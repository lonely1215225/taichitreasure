package edu.hunau.zzy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class CipherUtils {
    /**
     * 生成随机KEY
     */
    private static final Logger logger = LoggerFactory.getLogger(CipherUtils.class);

    public static Key getKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            //_generator.init(new SecureRandom(strKey.getBytes()));这种方式，在windows可以，可是在linux每次生成的key不一样
            // 指定了随机算法
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            generator.init(secureRandom);
            //generator.init(128);
            Key key = generator.generateKey();
            return key;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * @Description: 通过AES对文件进行加密上传，再将生成的密钥用RSA公钥进行加密
     * @Author: zzy
     * @Date: 2020/11/03
     * @param: is out
     * @return: String 返回经过RSA加密后的密钥
     * @Version: 1.0
     */
    private static PublicKey aPublic;
    private static PrivateKey aPrivate;

    public static String encrypt(InputStream is, OutputStream out, String publicKey) throws Exception {
        // 拿到AES加密对象
        Cipher cipher = Cipher.getInstance("AES");
        // 获取随机生成的密钥
        Key key = getKey();

        // 进行加密初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 加密输入流
        CipherInputStream cis = new CipherInputStream(is, cipher);
        // 加密上传
        cis.transferTo(out);
        // 关闭相关io流
        closeIO(is, out, cis, null);
        // 拿到密钥的字节数组
        byte[] encoded = key.getEncoded();

        Cipher rsaCipher = Cipher.getInstance("RSA");

        logger.info("从客户端传过来的公钥:" + publicKey);
        byte[] decode = Base64.getDecoder().decode(publicKey);

        KeyFactory rsa = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
        aPublic = rsa.generatePublic(keySpec);
        rsaCipher.init(Cipher.ENCRYPT_MODE, aPublic);
        // 加密随机生成的AES密钥
        byte[] cipherText = rsaCipher.doFinal(encoded);
        // 转码后的密钥密文
        String cipherTextStr = Base64.getEncoder().encodeToString(cipherText);

        logger.info("加密密文:" + cipherTextStr);
        return cipherTextStr;
    }

    // 关闭IO资源
    private static void closeIO(InputStream is, OutputStream out, CipherInputStream cis, CipherOutputStream cos) {
        try {
            if (cis != null)
                cis.close();
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
    public static boolean verify(String SID,String signature,String publicKey){
        String SIDBASE64 = null;
        String srcSID = null;
        try {
            byte[] decode = Base64.getDecoder().decode(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
            PublicKey pk = keyFactory.generatePublic(keySpec);
            logger.info("通过x509制造得到的公钥："+Base64.getEncoder().encodeToString(pk.getEncoded()));
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, pk);
            byte[] sign = Base64.getDecoder().decode(signature);
            logger.info("签名信息BASE64解码前:"+signature);

            byte[] signBytes = rsa.doFinal(sign);
             srcSID = Base64.getEncoder().encodeToString(signBytes);
            logger.info("解密后的签名："+srcSID);
             SIDBASE64 = Base64.getEncoder().encodeToString(SID.getBytes());

        }catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }finally {
            if (srcSID.equals(SIDBASE64)) return true;
            else return false;
        }

    }
}
