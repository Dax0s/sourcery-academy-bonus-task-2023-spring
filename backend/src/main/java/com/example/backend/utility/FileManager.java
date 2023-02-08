package com.example.backend.utility;


import org.springframework.web.multipart.MultipartFile;

public class FileManager {
    public static String getFileExtension(String fileName) throws IllegalArgumentException {
        int dotIndex = fileName.lastIndexOf(".");

        if (dotIndex == -1) {
            throw new IllegalArgumentException("Not a legal file name.");
        }

        return fileName.substring(dotIndex + 1);
    }

    public static boolean checkIfFileIsCsv(MultipartFile file) throws IllegalArgumentException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String extension = FileManager.getFileExtension(fileName);

        return extension.equals("csv");
    }
}
