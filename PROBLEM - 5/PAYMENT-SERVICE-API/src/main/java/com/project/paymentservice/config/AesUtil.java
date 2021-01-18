package com.project.paymentservice.config;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * The Class AesUtil.
 */
public class AesUtil {
    
    /** The key size. */
    private final int keySize;
    
    /** The iteration count. */
    private final int iterationCount;
    
    /** The cipher. */
    private final Cipher cipher;
    
    /**
     * Instantiates a new aes util.
     *
     * @param keySize the key size
     * @param iterationCount the iteration count
     */
    public AesUtil(int keySize, int iterationCount) {
        this.keySize = keySize;
        this.iterationCount = iterationCount;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw fail(e);
        }
    }
    
    /**
     * Decrypt.
     *
     * @param salt the salt
     * @param iv the iv
     * @param passphrase the passphrase
     * @param ciphertext the ciphertext
     * @return the string
     */
    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
        try {
            SecretKey key = generateKey(salt, passphrase);
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }catch (Exception e){
            return null;
        }
    }
    
    /**
     * Do final.
     *
     * @param encryptMode the encrypt mode
     * @param key the key
     * @param iv the iv
     * @param bytes the bytes
     * @return the byte[]
     */
    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return null;
        }
    }
    
    /**
     * Generate key.
     *
     * @param salt the salt
     * @param passphrase the passphrase
     * @return the secret key
     */
    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

    /**
     * Base 64.
     *
     * @param str the str
     * @return the byte[]
     */
    public static byte[] base64(String str) {
        return Base64.decodeBase64(str);
    }
    
    /**
     * Hex.
     *
     * @param str the str
     * @return the byte[]
     */
    public static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Fail.
     *
     * @param e the e
     * @return the illegal state exception
     */
    private IllegalStateException fail(Exception e) {
        return null;
    }

}