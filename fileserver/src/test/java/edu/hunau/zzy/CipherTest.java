package edu.hunau.zzy;

import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.*;
import java.util.Base64;

public class CipherTest {
    // DES的对称密钥
    SecretKey secretKey;

    @Test
    public void DESEncryptTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        KeyGenerator des = KeyGenerator.getInstance("AES");
        des.init(128);
        secretKey = des.generateKey();

        System.out.println("生成AES对称密钥为："+Base64.getEncoder().encodeToString(secretKey.getEncoded()));


        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        InputStream is=new FileInputStream("C:\\Users\\Administrator\\Desktop\\SRE工程师_张卓悦_本科_15574945470.pdf");
        OutputStream out=new FileOutputStream("D:\\IDEAWorkSpace\\IdeaProjects\\fileserver\\test.pdf");


        CipherInputStream cipherInputStream = new CipherInputStream(is,cipher);

        cipherInputStream.transferTo(out);

        is.close();
        out.close();
        cipherInputStream.close();

        // RSA加密密钥
        RSATest();
    }
    @Test
    public  void DESDecrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        Cipher des = Cipher.getInstance("AES");
        des.init(Cipher.DECRYPT_MODE,desKeySpec);

        InputStream is=new FileInputStream("D:\\IDEAWorkSpace\\IdeaProjects\\fileserver\\test.pdf");
        File file = new File("D:\\IDEAWorkSpace\\IdeaProjects\\fileserver\\downloadtest");
        if (!file.exists()) file.mkdirs();
        OutputStream out=new FileOutputStream("D:\\IDEAWorkSpace\\IdeaProjects\\fileserver\\downloadtest\\downloadTest.pdf");
        CipherOutputStream cipherOutputStream = new CipherOutputStream(out,des);

        is.transferTo(cipherOutputStream);

        is.close();
        out.close();
        cipherOutputStream.close();
    }
    SecretKeySpec desKeySpec;
    @Test
    public void RSATest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = rsa.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,aPublic);

        byte[] encoded = secretKey.getEncoded();

        byte[] cipherCodes = cipher.doFinal(encoded);

        System.out.println("加密后的AES对称密钥"+Base64.getEncoder().encodeToString(cipherCodes));

        cipher.init(Cipher.DECRYPT_MODE,aPrivate);
        byte[] decode = cipher.doFinal(cipherCodes);

        System.out.println("解密后的AES对称密钥"+Base64.getEncoder().encodeToString(decode));
        desKeySpec = new SecretKeySpec(decode,"AES");

//        SecretKeySpec desKeySpec = new SecretKeySpec(decode,"AES");
//
//
//        secretKey= SecretKeyFactory.getInstance("AES").generateSecret(desKeySpec);

        //System.out.println("通过RSA解密AES密钥得到的密钥："+Base64.getEncoder().encodeToString(secretKey.getEncoded()));

        DESDecrypt();

    }
}
