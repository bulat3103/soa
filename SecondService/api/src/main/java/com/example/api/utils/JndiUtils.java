package com.example.api.utils;

import javax.naming.InitialContext;

public class JndiUtils {
    @SuppressWarnings("unchecked")
    public static <T> T getFromContext(Class<T> clazz, String path) {
        try {
            return (T) new InitialContext().lookup(path);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to find ejb");
        }
    }
}
