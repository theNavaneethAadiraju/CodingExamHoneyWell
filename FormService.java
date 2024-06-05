package com.example.Test;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FormService {

    public void processForm(FormData formData) {
    }

    public Resource generateTextFile() throws IOException {
        FormData formData = new FormData(); 
        String content = "Serial ID: " + formData.getSerialId() + "\n" +
                         "Name: " + formData.getName() + "\n" +
                         "Number of Parts: " + formData.getNumberOfParts() + "\n" +
                         "Asset Type: " + formData.getAssertType();
        Files.write(Paths.get("formData.txt"), content.getBytes());
        return new ByteArrayResource(content.getBytes());
    }

    public Resource signFile(Resource file) throws IOException, NoSuchAlgorithmException {
        byte[] fileBytes = file.getInputStream().readAllBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(fileBytes);

        StringBuilder signedContent = new StringBuilder();
        signedContent.append("SHA-256 Hash: ").append(bytesToHex(hash)).append("\n")
                     .append("Original File Content:\n")
                     .append(new String(fileBytes));

        Files.write(Paths.get("signedFile.txt"), signedContent.toString().getBytes());
        return new ByteArrayResource(signedContent.toString().getBytes());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}

