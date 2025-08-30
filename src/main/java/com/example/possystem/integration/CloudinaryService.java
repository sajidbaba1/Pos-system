package com.example.possystem.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    // TODO: Add your Cloudinary configuration here
    // private Cloudinary cloudinary;

    // public CloudinaryService() {
    //     Map<String, String> config = new HashMap<>();
    //     config.put("cloud_name", "YOUR_CLOUD_NAME");
    //     config.put("api_key", "YOUR_API_KEY");
    //     config.put("api_secret", "YOUR_API_SECRET");
    //     cloudinary = new Cloudinary(config);
    // }

    public String uploadFile(MultipartFile file) throws IOException {
        System.out.println("--- Placeholder: Uploading file to Cloudinary ---");
        // Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        // return uploadResult.get("url").toString();
        return "http://placeholder.com/image.jpg";
    }
}
