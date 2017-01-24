package com.alcatelsbell.nms.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午4:09
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class ByteUtil {
    public static int subByteArray(byte[] parent,byte[] sub) {
       for (int i = 0; i <= parent.length - sub.length; i++) {
            boolean  match = true;
            for (int j = 0; j < sub.length ; j++) {
                if (parent[i+j] != sub[j]) {
                    match = false;
                    break;
                }
            }

           if (match) return i;
       }
        return -1;
    }

    public static byte[] subByteArray(byte[] parent,int from, int length) {
        byte[] result = new byte[length];
        System.arraycopy(parent,from,result,0,length);
        return result;
    }


    public static int createUnsignedInt(byte[] bs) {
        int result = -1;
        for (int i = 0; i < bs.length; i++) {
            int a = bs[i] << (bs.length-i-1) * 8;
            if (i == 0)
                result = a;
            else result = result | a;

        }
        return result;
    }


    public static Object bytesToObject(Field headField, byte[] fieldBytes) throws Exception {
        Class<?> type = headField.getType();
        Object fieldValue = null;
        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            fieldValue = ByteUtil.createUnsignedInt(fieldBytes);
        } else if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
            if (fieldBytes.length > 1)
                throw new Exception("byte type field : "+headField+" size > 1");
            fieldValue = fieldBytes[0];
        } else if (type.equals(String.class)) {
            fieldValue = new String(fieldBytes);
        }
        else if (type.equals((new byte[0]).getClass())) {
            fieldValue = (fieldBytes);
        }
          else  {
            throw new Exception(" unsupported object type : "+type);
        }


        return fieldValue;

    }

    public static byte[] objectToBytes(Object value,int byteSize) {
        if (value instanceof Byte) {
            return new byte[]{((Byte) value).byteValue()};
        }
        if (value instanceof byte[]) {
            return (byte[])value;
        }
        if (value instanceof Integer) {
            return int2byte((Integer)value,byteSize);
        }
        if (value instanceof Long) {
            return long2byte((Long)value,byteSize);
        }
        if (value instanceof String) {
            byte[] bytes = ((String) value).getBytes();
            if (bytes.length < byteSize) {
                byte[] bs = new byte[byteSize];
                System.arraycopy(bytes,0,bs,0,bytes.length);
                return bs;
            } else if (bytes.length > byteSize){
                byte[] bs = new byte[byteSize];
                System.arraycopy(bytes,0,bs,0,byteSize);
                return bs;
            } else
                return bytes;
        }
        return new byte[0];
    }



    public static byte[] int2byte(int number, int size){
        byte[] byt = new byte[size];
        for (int i = 0; i < byt.length; i++) {
            if (i == byt.length-1)
                byt[i] = (byte)(number&0xff);
            else
                byt[i] =  (byte)(number>>((byt.length-1-i) * 8)&0xff);
        }
        return byt;
    }



    public static byte[] long2byte(long number, int size){
        byte[] byt = new byte[size];
        for (int i = 0; i < byt.length; i++) {
            if (i == 0)
                byt[i] = (byte)(number&0xff);
            else
                byt[i] =  (byte)(number>>(i * 8)&0xff);
        }
        return byt;
    }
    public static byte[] joint(byte[]... arrays) {
        int length = 0;
        for (int i = 0; i < arrays.length; i++) {
            byte[] array = arrays[i];
            length += array.length;
        }
        byte[] newarray = new byte[length];
        int current = 0;
        for (int i = 0; i < arrays.length; i++) {
            byte[] array = arrays[i];
            System.arraycopy(array,0,newarray,current,array.length);
            current += array.length;
        }

        return newarray;
    }
    public static String byteArrayToString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        if (bytes != null) {
            for (int i = 0; i < bytes.length; i++) {
                byte aByte = bytes[i];
                sb.append(aByte).append(",");
            }
        }
        sb.append("}") ;
        return sb.toString();
    }

    public static boolean byteArrayEquals(byte[] bs1,byte[] bs2) {
        if (bs1.length != bs2.length) return false;
        for (int i = 0; i < bs1.length; i++) {
            byte b = bs1[i];
            byte b2 = bs2[i];
            if (b != b2) return false;
        }
        return true;
    }
    public static void main(String[] args) {

        byte[] x = int2byte(32,4);
        System.out.println(x);
    }

    public static void logBytes(byte[] bytes,String fileName) {
        try {
            FileOutputStream ffos = (new FileOutputStream(fileName));
            ;
            ffos.write(bytes);
            ffos.close();
        } catch (IOException e) {
            LogUtil.error(ByteUtil.class, e, e);
        }
    }
}
