package com.cornflower.kotlin.utils.appUpdate;

import com.cornflower.kotlin.utils.IOUtils;
import com.cornflower.kotlin.utils.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类.
 * 基于MD5
 */
public class MD5Utils {
    private final static String[] MD5hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static byte[] getMD5ByteArray(String content) {
        byte[] source = null;
        MessageDigest md = null;
        try {
            source = content.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(source);
        return md.digest();
    }
    
    /**
     * 获取MD5加密后的小写字符串
     * 
     * @return
     */
    public static String getMD5String(String originString) {
        if (originString != null) {
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes("UTF-8"));
                String resultString = byteArrayToHexString(results);
                return resultString;
            } catch(Exception ex) {
                LogManager.e("异常",ex);
            }
        }
        return null;
    }


    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return MD5hexDigits[d1] + MD5hexDigits[d2];
    }

    /**
     * 生成文件的md5校验值
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) {
        InputStream fis = null;
        MessageDigest messagedigest = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int numRead = 0;
             messagedigest = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
        }catch (Exception e){
           LogManager.e("MD5Utils:",e);
        }finally {
            IOUtils.close(fis);
        }
         if(messagedigest==null){
            return "";
         }

        return byteArrayToHexString(messagedigest.digest());
    }

}
