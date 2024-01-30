package com.steffi.dorfladen.web.security;

import java.io.UnsupportedEncodingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.steffi.dorfladen.web.beans.Benutzer;

@Service
public class PasswortHashing {

    private String keyString;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    private SecretKey secKey;

    public PasswortHashing(String jwtSecretKey) {
        try{
        this.keyString = jwtSecretKey;            
        this.secKey = initSecretKey();
        } catch (Exception ex){
            System.out.println(ex.getCause());
        }
    }

    public SecretKey gSecretKey(){
        return secKey;
    }

    // public String encrypt(String rawPassword) {
    //     return encoder.encode(rawPassword);
    // }

    public boolean isPasswordKorrekt(String pw, Benutzer benutzer) {
        return encoder.matches(pw, benutzer.getPasswortHashed());
    }

    // public String decrypt(String cryptedPassword) {
    //     try {
    //         byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(cryptedPassword));
    //         return new String(decryptedPasswordBytes, "UTF-8");
    //     } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //         return "Fehler";
    //     }
    // }

    private SecretKey initSecretKey() {
        try {
            byte[] keyBytes = keyString.getBytes("UTF-8");
            return new SecretKeySpec(keyBytes, "HmacSHA512");
        } catch (UnsupportedEncodingException e) {
            // TODO: ordentliche Fehlerbehandlung implementieren
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
    // String rawPassword = "testitest";

    // String hashedPassword = "$2a$10$ChBuxoeT8OgaiZ0uvjHVFeLFe2oDhBU0PgqjuDy9XO6RYMO2.P.1S";

    // boolean isMatch = passwordEncoder.matches(rawPassword, hashedPassword);
    // System.out.println(isMatch);

    // var ben = new Benutzer() {
    //     @Override
    //     public String getPasswortHashed() {
    //         return hashedPassword;
    //     }

    // };

    // System.out.println(isPasswordKorrekt(rawPassword, ben));
    // }
}