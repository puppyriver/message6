package com.alcatelsbell.nms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author: Ronnie.Chen
 * Date: 12-12-28
 * Time: 下午12:51
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class MD5Util {
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void md5s(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        //    str = buf.toString();
            System.out.println("result: " + buf.toString());// 32位的加密
            System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
    public static void main(String[] args) {
    //    System.out.println(MD5Util.MD5("5620Ldap!"));
        //79E4DCD57E7164336DBE714F45043C16
         MD5Util.md5s("maestro2213411");
     //   System.out.println(MD5Util.MD5("加密"));
    }
}