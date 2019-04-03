package com.neuedu.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import com.sun.org.apache.xml.internal.security.Init;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by HP on 2019/4/2.
 */
public class DESUTIL {
    static Key key;
    static String KEYSTR="abc";
    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEYSTR.getBytes());
            keyGenerator.init(secureRandom);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static String encode(String psd){
        String result="";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] b = cipher.doFinal(psd.getBytes());
            result = base64Encoder.encode(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decode(String str){
        String result
    }
    public static void main(String[] args) {
        System.out.println(encode("123456"));
    }
}
