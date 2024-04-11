package org.opensingular.dbuserprovider.util;

import java.util.Base64;
import java.util.Objects;
import java.math.BigInteger;
import lombok.extern.jbosslog.JBossLog;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@JBossLog
public class PBKDF2SHA256HashingUtil {

    private char[] password;
    private byte[] salt;
    private int iterations;
    private static final int keyLength = 256;
    /**
     * @param password
     * @param salt
     * @param iterations
     */
    public PBKDF2SHA256HashingUtil(String password, String salt, int iterations){
        this.password = password.toCharArray();
        this.salt = salt.getBytes();
        this.iterations = iterations;
    }

    public boolean validatePassword(String passwordHash, String cipherTextType){
        return Objects.equals(passwordHash, hashPassword(cipherTextType));
    }

    private String hashPassword(String cipherTextType){
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(this.password, this.salt, this.iterations, keyLength);

            SecretKey key = skf.generateSecret(spec);
            if(cipherTextType.equals("HEX")){
                return this.toHex(key.getEncoded());
            }
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
}
