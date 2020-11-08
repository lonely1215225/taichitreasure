package edu.hunau.zzy;

import java.security.*;
import java.util.Base64;

public class SignatureTest {
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();

        String input="123";

        String signatureData=getSignature(input,"sha256withrsa",aPrivate);

        System.out.println(signatureData);
        boolean verify=verifySignature(input,"sha256withrsa",aPublic,signatureData);
        System.out.println(verify);
    }

    /**
     *
     * @param input
     * @param algorithm
     * @param aPublic
     * @param signatureData
     * @return
     */
    private static boolean verifySignature(String input, String algorithm, PublicKey aPublic, String signatureData)throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(aPublic);
        signature.update(input.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureData));

    }

    private static String getSignature(String pid, String algorithm, PrivateKey aPrivate) throws Exception {
        Signature signature = Signature.getInstance(algorithm);

        signature.initSign(aPrivate);

        signature.update(pid.getBytes());

        byte[] sign = signature.sign();

        String s = Base64.getEncoder().encodeToString(sign);

        return s;
    }
}
