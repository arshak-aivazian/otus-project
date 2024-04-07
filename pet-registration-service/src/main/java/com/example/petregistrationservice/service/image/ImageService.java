package com.example.petregistrationservice.service.image;

import java.io.InputStream;
import java.util.Optional;

public interface ImageService {
    void upload(String imagePath, InputStream content);
    Optional<byte[]> get(String imagePath);
}
