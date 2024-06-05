package com.example.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping("/submitForm")
    public ResponseEntity<?> submitForm(@RequestBody FormData formData) {
        formService.processForm(formData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Resource file = formService.generateTextFile();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/signFile")
    public ResponseEntity<Resource> signFile(@RequestBody Resource file) throws IOException, NoSuchAlgorithmException {
        Resource signedFile = formService.signFile(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + signedFile.getFilename() + "\"")
                .body(signedFile);
    }
}
