package com.steffi.dorfladen.web.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.steffi.dorfladen.web.beans.Benutzer;

public class PasswortHashing {

    private static final BCryptPasswordEncoder BCRYPT_INSTANCE = new BCryptPasswordEncoder();
    private static final SecretKey SecKey_INSTANCE = initSecretKey();
    private static final Cipher cipher = initCipher();

    private PasswortHashing() {
        // DoNothing
    }

    public static String encrypt(String rawPassword) {
        return BCRYPT_INSTANCE.encode(rawPassword);
    }

    public static boolean isPasswordKorrekt(String pw, Benutzer benutzer) {
        return BCRYPT_INSTANCE.matches(pw, benutzer.getPasswortHashed());
    }

    public static String decrypt(String cryptedPassword) {
        try {
            byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(cryptedPassword));
            return new String(decryptedPasswordBytes, "UTF-8");
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Fehler";
        }
    }

    private static SecretKey initSecretKey() {
        String keyString = "EineInselMit2Ber"; // Schlüssel aus sicherer Quelle holen

        // Entschlüsselung des Passworts
        try {
            byte[] keyBytes = keyString.getBytes("UTF-8");
            return new SecretKeySpec(keyBytes, "AES");
        } catch (UnsupportedEncodingException e) {
            // TODO: ordentliche Fehlerbehandlung implementieren
            e.printStackTrace();
            return null;
        }
    }

    private static Cipher initCipher() {
        try {
            var cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, SecKey_INSTANCE);
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // public static void main(String[] args) throws Exception {
    //     // Daten für Verschlüsselung
    //     byte[] dataToEncrypt = "ThisIsATest".getBytes("UTF-8");

    //     // Verschlüsselung
    //     Cipher cipher = Cipher.getInstance("AES");
    //     cipher.init(Cipher.ENCRYPT_MODE, SecKey_INSTANCE);
    //     byte[] encryptedData = cipher.doFinal(dataToEncrypt);

    //     var keyString = Base64.getEncoder().encodeToString(encryptedData);

    //     // Entschlüsselung des Passwort
    //     cipher.init(Cipher.DECRYPT_MODE, SecKey_INSTANCE);

    //     byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(keyString));
    //     String decryptedPassword = new String(decryptedPasswordBytes, "UTF-8");

    //     System.out.println("Original: " + new String(dataToEncrypt));
    //     System.out.println("Verschlüsselt: " + keyString);
    //     System.out.println("Entschlüsselt: " + decryptedPassword);
    // }

    // public static void main(String[] args) {
    // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // String rawPassword = "ThisIsATest";

    // String hashedPassword = passwordEncoder.encode(rawPassword);

    // System.out.println("Gehashtes Passwort: " + hashedPassword);

    // boolean isMatch = passwordEncoder.matches(rawPassword, hashedPassword);
    // System.out.println(isMatch);
    // }
}