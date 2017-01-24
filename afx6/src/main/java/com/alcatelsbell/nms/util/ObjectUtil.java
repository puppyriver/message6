package com.alcatelsbell.nms.util;

import com.alcatelsbell.nms.common.SysUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * User: Ronnie.Chen
 * Date: 11-5-6
 * Time: 上午9:04
 */
public class ObjectUtil {
    public static Object readObject(String fileName) {
        return readObjectByPath("../cache/" + fileName);
    }
    public static Object readObjectByPath(String fileName)
    {
        FileInputStream inputFile = null;

        ObjectInputStream serializeStream = null;
        FileChannel fc = null;
        try {
            long t1 = System.currentTimeMillis();
            //System.out.println(new File("../testpath").getAbsolutePath());
            inputFile = new FileInputStream(fileName);
            int size = inputFile.available();
            fc = inputFile.getChannel();

            ByteBuffer bb = ByteBuffer.allocate(size);
            fc.read(bb);
            long t2 = System.currentTimeMillis();
            System.out.println("read to mem spend : " + (t2 - t1));
            //bb.flip();
            // inputFile = new
            // FileInputStream("../cache/"+getDtString()+fileName);
            serializeStream = new ObjectInputStream(new ByteArrayInputStream(bb.array()));
            return serializeStream.readObject();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.out.println("resoure file not found !" + fileName);
            return null;
        } finally {
            try {
                if (serializeStream != null)
                    serializeStream.close();
                if (fc != null) {
                    fc.close();
                }
                if (inputFile != null)
                    inputFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static boolean saveObject(String objname,Object object,long sencodes) {
        final Object _obj = object;
        final String name = objname;
        final long scs = sencodes;
        if (object != null && sencodes > 0) {
            Thread t = new Thread() {
                public void run() {
                    String fileName = name;
                    while (true) {
                        try {
                            Thread.sleep(scs * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (_obj) {
                            saveObject(name+"."+ SysUtil.getDayString(new Date()),_obj);
                         }
                    }
                }
            };
            t.start();
            return true;
        }
        return false;
    }
    public static boolean saveObject(String objname, Object object) {
        if(object==null){
            System.out.println("Error args object");
            return false;
        }
        FileOutputStream outputFile = null;
        ObjectOutputStream serializeStream = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        FileChannel fc = null;
        try {
            if (!newFolder("../cache")) {
                return false;
            }

            serializeStream = new ObjectOutputStream(bao);
            serializeStream.writeObject(object);
            serializeStream.flush();
            fc = new FileOutputStream("../cache/" + objname).getChannel();
            fc.write(ByteBuffer.wrap(bao.toByteArray()));

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }
                if (bao != null)
                    bao.close();
                if (serializeStream != null)
                    serializeStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                //Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent     String
     * @return boolean
     */
    public static boolean delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
            return false;
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
