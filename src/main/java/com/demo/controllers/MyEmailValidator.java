package com.demo.controllers;

public class MyEmailValidator {
    public static boolean validate(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= email.length() - 1) {
            return false;
        }

        return true;
    }
}