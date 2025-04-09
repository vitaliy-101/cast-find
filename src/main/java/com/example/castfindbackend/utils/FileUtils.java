package com.example.castfindbackend.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@UtilityClass
public class FileUtils {

    public static List<byte[]> getByteFromMultipartList(List<MultipartFile> multipartFiles) {
        var files = multipartFiles.stream()
                .map(FileUtils::getByteFromMultipart)
                .toList();
        if (files.isEmpty()) {
            throw new IllegalArgumentException("No image data provided");
        }
        return files;
    }

    public static byte[] getByteFromMultipart(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToUrl(String url, String id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(url)
                .path(id)
                .toUriString();
    }
}