package com.example.car_sharing_backend.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final Cloudinary cloudinary;

    public String uploadPhoto(MultipartFile file, String folder) throws IOException {

        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("File is empty");

        Map<String, Object> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", folder)
        );

        return result.get("secure_url").toString();
    }
}
