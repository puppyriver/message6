package com.alcatelsbell.nms.db.components.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Ronnie.Chen
 * Date: 11-8-17
 */
public class BinaryObjectUtil {
    public static synchronized void saveObject(String key,byte[] bs) {
         saveObjectImpl("BINARYOBJECT_" + key, bs);
    }


    public static synchronized byte[] readObject(String key) {
        Object o = readObjectImpl("BINARYOBJECT_" + key);
        if (o != null && o instanceof  byte[])
            return (byte[])o;
        return null;
    }
    
    public static synchronized List readObjectAll() {
        Object o = readObjectAllImpl();
        if (o != null && o instanceof  List)
            return (List)o;
        return null;
    }

    public static void main(String[] args) {
        BinaryObjectUtil.saveObject("123",new byte[]{1,2,3,4,5,6,7,8,9});
        byte [] bb = BinaryObjectUtil.readObject("123");
        System.out.println(bb);
    }
    public static byte[] readObjectImpl(String objname) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("../BO/"+objname);
            int av = fis.available();
            byte[] bs = new byte[av];
            fis.read(bs);
             return bs;
        } catch ( Exception e) {
           e.printStackTrace();
        } finally {
            if (fis != null) try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         return null;
    }
    
    public static List readObjectAllImpl() {
        FileInputStream fis = null;
        String filePath="../BO/";
        File file=new File(filePath);
		File[] subfile=file.listFiles();
		List list = new ArrayList();
		for (int i = 0; i < subfile.length; i++) {
			if (!subfile[i].isDirectory()) {
				try {
					fis = new FileInputStream(subfile[i]);
					int av = fis.available();
					byte[] bs = new byte[av];
					fis.read(bs);
					list.add(bs);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		if (fis != null) try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return list;
    }
    
    public static boolean saveObjectImpl(String objname, byte[] bs) {


        FileChannel fc = null;
        try {
            if (!newFolder("../BO")) {
                return false;
            }


            fc = new FileOutputStream("../BO/" + objname).getChannel();
            fc.write(ByteBuffer.wrap(bs));

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                //Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    public static boolean newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}


