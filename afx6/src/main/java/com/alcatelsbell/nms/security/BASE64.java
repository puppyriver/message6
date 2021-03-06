package com.alcatelsbell.nms.security;

import sun.misc.BASE64Decoder;   
import sun.misc.BASE64Encoder;   
  
/**  
 * BASE64加密解密  
 */  
public class BASE64   
{   
  
    /**   
     * BASE64解密  
   * @param key         
     * @return         
     * @throws Exception         
     */            
    public static byte[] decryptBASE64(String key) throws Exception {
        if (key == null) return new byte[0];
        return (new BASE64Decoder()).decodeBuffer(key);             
    }             
                
    /**        
     * BASE64加密  
   * @param key         
     * @return         
     * @throws Exception         
     */            
    public static String encryptBASE64(byte[] key) throws Exception {             
        return (new BASE64Encoder()).encodeBuffer(key);             
    }     
       
    public static void main(String[] args) throws Exception   
    {   
        String data = BASE64.encryptBASE64("xiaoting6734.,/".getBytes());   
        System.out.println("加密前："+data);   
           
        byte[] byteArray = BASE64.decryptBASE64(data);   
        System.out.println("解密后："+new String(byteArray));   
    }   
}  

