package ru.itis.service;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;
import ru.itis.db.BlockModel;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class SignService {
    public static final String DIGEST_ALGORITHM = "SHA-256";
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHM = "SHA256withRSA";

    public static String publicKey16 ="30819f300d06092a864886f70d010101050003818d00308189028181008af59956f7923bb05d9edd3743c07cf9e89d1245346c108af4ea4fdb0e46663eeb3703201c77ebe253400ea7bec329087a375a203fe81cc02fdbbcd6499d38dd8b5d35a948399f349b28e290c658aacbed4f89d89d04b006c6d2e59e645c368b9ec2e5c54afa6d2bb160b9ca6ec99472fb2d46dbd1ae354cdf2542c77eeb8bd90203010001";
    public static String privateKey16 ="30820276020100300d06092a864886f70d0101010500048202603082025c020100028181008af59956f7923bb05d9edd3743c07cf9e89d1245346c108af4ea4fdb0e46663eeb3703201c77ebe253400ea7bec329087a375a203fe81cc02fdbbcd6499d38dd8b5d35a948399f349b28e290c658aacbed4f89d89d04b006c6d2e59e645c368b9ec2e5c54afa6d2bb160b9ca6ec99472fb2d46dbd1ae354cdf2542c77eeb8bd9020301000102818074b79bc79f84f59bcad7304a461f3d1a1017368c685ec87a5f031c760b8478472afc772496a4e7bd9f5d73b1d41a4ab1f32de106ab8703f72d7bad1e7490997cbfb80ba34df25cb7f745d06d4f1d8ddb7412f102383d1157f1671068736be1bddbe31df3547719fdbddb892c8792c391bffcadb4be320fc02dab468438297671024100ec2ff31bd5dc861a19d7e63b879ee815ed2fc051359262b9267854de589674593dafa25bfa551ea0c05c12497c2810c7125e61ebe371ddb6619b71216d76d4f5024100969db5894951bbce90e593d5861b9e37c66641c72abb60daab8b02e5640fe21e98eef17036ebe1baba0fa389557c9b539b3e7420592147dcf7c467edfb416cd502401b419151960583e841313c1d84921f0060c5d0f0ce38dd71b671ce817641ae9d99d3c9edc95ec7510fc1d04e70a882ff262c842a5fae054305d29564a128447d02410091f0728c79fcb66bab3ea8864711cfb6df8909c780825b90c69345d51ba5c046418d956853ef2fa40cd14676b5dcad6ccf8a2e5f81bd9bb262696f02cb685399024049674f850dfbfc8f87a3fb5623eaa0a1e512fb920332792d866c88d79ab79710e2f31c4c7aa5241b306905152a82a30e23abcc31fb3de3614c5523523ef79808";

    public static byte[] getHash(BlockModel block) throws  UnsupportedEncodingException, NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM);

        byte[] result = digest.digest(
                concat(concat(block.getPrevhash() != null? block.getPrevhash().getBytes() : null,
                                block.getData().toString().getBytes("UTF-8")),
                        block.getTs().getBytes()));
        return result;
    }
    public static byte[] concat(byte[] a, byte[] b) {
        if (a == null) return b;
        if (b == null) return a;
        int len_a = a.length;
        int len_b = b.length;
        byte[] C = new byte[len_a + len_b];
        System.arraycopy(a, 0, C, 0, len_a);
        System.arraycopy(b, 0, C, len_a, len_b);
        return C;
    }
    public static KeyPair loadKeys() throws Exception {

        byte[] publicKeyHex = publicKey16.getBytes();
        byte[] privateKeyHex = privateKey16.getBytes();

        PublicKey publicKey = convertArrayToPublicKey(Hex.decode(publicKeyHex),KEY_ALGORITHM);
        PrivateKey privateKey = convertArrayToPrivateKey(Hex.decode(privateKeyHex),KEY_ALGORITHM);

        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        return keyPair;
    }
    public static PublicKey convertArrayToPublicKey(byte encoded[], String algorithm) throws Exception {
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        return pubKey;
    }
    public static PrivateKey convertArrayToPrivateKey(byte encoded[], String algorithm) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        return priKey;
    }
    public byte[] generateRSAPSSSignature(PrivateKey privateKey, byte[] input)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);

        signature.initSign(privateKey);

        signature.update(input);

        return signature.sign();
    }

    public static byte[] generateRSAPSSSignature(byte[] input)
            throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);

        signature.initSign(convertArrayToPrivateKey(Hex.decode(privateKey16), KEY_ALGORITHM));

        signature.update(input);

        return signature.sign();
    }

    public static boolean verifyRSAPSSSignature(PublicKey publicKey, byte[] input, byte[] encSignature)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);

        signature.update(input);

        return signature.verify(encSignature);
    }
    public static boolean verifySignature(String publicKey, String data, String blockSignature) {
        Signature signature;
        if (blockSignature == null || publicKey == null) {
            return false;
        }try {
            signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initVerify(convertArrayToPublicKey(Hex.decode(publicKey), KEY_ALGORITHM));
            signature.update(data.toString().getBytes(StandardCharsets.UTF_8));
            return signature.verify(Hex.decode(blockSignature));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}


