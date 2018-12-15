package util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	// ******************************javaapi BASE64编码***************************  
	  
    /** 
     * 使用Base64对字符串进行编码 
     *  
     * @param binaryData 
     * @return 
     */  
    public static String getBASE64(String str) {  
        // if (s == null)  
        // return null;  
        // return (new sun.misc.BASE64Encoder()).encode(s.getBytes());  
  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    /** 
     * 
     *  
     * 使用Base64对字符串进行解码 
     *  
     * @param base64String 
     * @return 
     */  
    public static String getFromBASE64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
  
}  
  
