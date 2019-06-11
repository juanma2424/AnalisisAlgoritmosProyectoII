package encriptacion;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lib.Logger;
import org.apache.commons.codec.binary.Base64;

public class TresDes implements IAlgorithm {
    private String key1;
    private String key2;

    public TresDes() {
        key1 = "";
        key2 = "";
    }

    public String encrypt(String pText) {
        MessageDigest md;
        String encryptedResult = "";
        try {
            md = MessageDigest.getInstance("md5");
            byte[] digestOfPassword = md.digest("fdsfsd".getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainTextBytes = pText.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);

            encryptedResult = new String(base64Bytes);
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return encryptedResult;
    }

    public String decrypt(String pText, String pKey) {
        String encryptedResult = "";
        try {
            byte[] message = Base64.decodeBase64(pText.getBytes("utf-8"));

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(pKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] plainText = decipher.doFinal(message);

            encryptedResult = new String(plainText, "UTF-8");
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return encryptedResult;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}