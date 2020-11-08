package edu.hunan.zzy.fileclient.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import java.util.Base64;
import java.util.UUID;

/**
 * @Description: 密文工具类，主要是用于签名和文件解密操作
 * @Author: zzy
 * @Date: 2020-11-06
 */
public class CipherUtils {
    private static final Logger logger = LoggerFactory.getLogger(CipherUtils.class);

    private  KeyPairGenerator rsa = null;
    private  PrivateKey aPrivate;
    private  PublicKey aPublic;

    // 初始化生成公钥和私钥
    public CipherUtils(){
        try {
            rsa = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = rsa.generateKeyPair();
            aPrivate = keyPair.getPrivate();
            aPublic = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public PrivateKey getaPrivate() {
        return aPrivate;
    }

    /**
     * @Description: 对上传过程进行签名
     * @param aPrivate 通过私钥进行签名
     * @return 返回SID和signature
     */
    public String signId(PrivateKey aPrivate){
        String signed=null;
        String randomUUID = null;
        try {
            randomUUID= UUID.randomUUID().toString();
            logger.info("随机生成的字符串："+randomUUID);
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE,aPrivate);
            byte[] bytes = rsa.doFinal(randomUUID.getBytes());
            signed = Base64.getEncoder().encodeToString(bytes);
            logger.info("用RSA私钥对SID进行加密后得到："+signed);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return randomUUID+","+signed;
    }
    public  String getPublicKey(){
        byte[] encoded = aPublic.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    public void decryptAndDownload(FileInputStream is, OutputStream out, String secretKey) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
            // 去数据库查到密文
            logger.info("数据库中的密文：" + secretKey);
            // 填充规范的密钥格式
            String cipherText = secretKey.replace(" ", "+");
            System.out.println(cipherText);
            byte[] decode = Base64.getDecoder().decode(cipherText);
            for (byte b : decode) {
                System.out.print(b);
            }
            System.out.println();
            // RSA通过私钥进行解密，得到AES密钥
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, aPrivate);
            byte[] plainText = rsaCipher.doFinal(decode);

            // 根据一个字节数组构造一个 SecretKey
            SecretKeySpec secretKeySpec = new SecretKeySpec(plainText, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 解密输出下载
            CipherOutputStream cos = new CipherOutputStream(out, cipher);

            is.transferTo(cos);
            closeIO(is, out, cos);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
    }
    // 关闭IO资源
    private static void closeIO(InputStream is, OutputStream out, CipherOutputStream cos) {
        try {
            if (is != null)
                is.close();
            if (out != null)
                out.close();
            if (cos != null)
                cos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
