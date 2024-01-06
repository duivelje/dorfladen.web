package com.steffi.dorfladen.web.Datenbank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.steffi.dorfladen.web.Beans.Benutzer;


public class PasswordHashing {
    
    private static final BCryptPasswordEncoder BCRYPT_INSTANCE = new BCryptPasswordEncoder();
    private static final PasswordHashing INSTANCE = new PasswordHashing();

    private PasswordHashing(){
        //DoNothing
    }

    public static PasswordHashing getInstance(){
        return INSTANCE;
    }
    
    public static String encrypt(String rawPassword) {
        return BCRYPT_INSTANCE.encode(rawPassword);
    }

    public static boolean isPasswordKorrekt(String pw, Benutzer benutzer){
        return BCRYPT_INSTANCE.matches(pw, benutzer.getPasswortHashed());
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "meinSicheresPasswort";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Gehashtes Passwort: " + hashedPassword);

        boolean isMatch = passwordEncoder.matches(rawPassword, hashedPassword);
        System.out.println(isMatch);
    }
}