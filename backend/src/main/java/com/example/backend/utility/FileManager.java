package com.example.backend.utility;


public class FileManager {
    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");

        if (dotIndex == -1) {
            throw new IllegalArgumentException("Not a legal file.");
        }

        return fileName.substring(dotIndex + 1);
    }
}
