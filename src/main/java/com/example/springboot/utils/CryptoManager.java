package com.example.springboot.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoManager {
    public static String[] encryptPassword(String password) {
        String[] passwordAndSalt = new String[2];
        String salt = BCrypt.gensalt();
        String passwordEncrypted = BCrypt.hashpw(password,salt);
        passwordAndSalt[0] = passwordEncrypted;
        passwordAndSalt[1] = salt;

        return passwordAndSalt;
    }
}


