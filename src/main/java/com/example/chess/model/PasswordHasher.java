package com.example.chess.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class PasswordHasher provides functionality for hashing passwords.
 *
 * @auther
 */
public class PasswordHasher {

    /**
     * Hashes the provided password using the SHA-256 algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password in hexadecimal representation, or {@code null} if an error occurred
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts the provided byte array to a hexadecimal string representation.
     *
     * @param hash the byte array to be converted
     * @return the hexadecimal string representation of the byte array
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}