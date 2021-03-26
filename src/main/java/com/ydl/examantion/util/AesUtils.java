package com.ydl.examantion.util;

import org.apache.axis.encoding.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AesUtils {
    /**
     * 偏移量
     */
    public  static String cKey="examplatformexam";
    /**
     * 算法/模式/补充
     */
    public static String CBC_MODEL="AES/CBC/NoPadding";

    /**
     * 算法/模式/补充
     */
    public static String sKey="jtt7xr+66mjfkhov";

    /**
     * 填充方式为NoPadding时，最后一个块的填充内容由程序员确定，通常为0.
     * AES/CBC/NoPadding加密的明文长度必须是16的整数倍，明文长度不满足16时，程序员要扩充到16的整数倍
     *
     * @param sSrc
     * @param aesKey
     * @return
     */
    public static String aesCbcNoPaddingEncrypt(byte[] sSrc, String aesKey) {
        //加密的数据长度不是16的整数倍时，原始数据后面补0，直到长度满足16的整数倍
        int len = sSrc.length;
        //计算补0后的长度
        while (len % 16 != 0) len++;
        byte[] result = new byte[len];
        //在最后补0
        for (int i = 0; i < len; ++i) {
            if (i < sSrc.length) {
                result[i] = sSrc[i];
            } else {
                //填充字符'a'
                //result[i] = 'a';
                result[i] = 0;
            }
        }
        SecretKeySpec skeySpec = create128BitsKey(aesKey);
        //使用CBC模式，需要一个初始向量iv，可增加加密算法的强度
        IvParameterSpec iv = create128BitsIV(cKey);
        Cipher cipher = null;
        try {
            //算法/模式/补码方式
            cipher = Cipher.getInstance(CBC_MODEL);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encrypted = null;
        try {
            encrypted = cipher.doFinal(result);
            return new String(new Base64().encode(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * JDK只支持AES-128加密，也就是密钥长度必须是128bit；参数为密钥key，key的长度小于16字符时用"0"补充，key长度大于16字符时截取前16位
     **/
    private static SecretKeySpec create128BitsKey(String key) {
        if (key == null) {
            key = "";
        }
        byte[] data = null;
        StringBuffer buffer = new StringBuffer(16);
        buffer.append(key);
        //小于16后面补0
        while (buffer.length() < 16) {
            buffer.append("0");
        }
        //大于16，截取前16个字符
        if (buffer.length() > 16) {
            buffer.setLength(16);
        }
        try {
            data = buffer.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");

    }


    /**
     * 创建128位的偏移量，iv的长度小于16时后面补0，大于16，截取前16个字符;
     *
     * @param iv
     * @return
     */
    private static IvParameterSpec create128BitsIV(String iv) {
        if (iv == null) {
            iv = "";
        }
        byte[] data = null;
        StringBuffer buffer = new StringBuffer(16);
        buffer.append(iv);
        while (buffer.length() < 16) {
            buffer.append("0");
        }
        if (buffer.length() > 16) {
            buffer.setLength(16);
        }
        try {
            data = buffer.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }


    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(CBC_MODEL);
            IvParameterSpec iv = new IvParameterSpec(cKey.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString.trim();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }


    public static void main(String[] args) throws Exception {
        String key="jtt7xr+66mjfkhov=1ch)49&ellkuqx-pm6n1e$=ta+j9gh9m*";
        key=key.substring(0,16);
        String encrypt = AesUtils.aesCbcNoPaddingEncrypt("123".getBytes(), key);
        System.out.println(encrypt);
       // AesUtils.Decrypt(encrypt,key);
         System.out.println(AesUtils.Decrypt(encrypt, key));
    }
}
