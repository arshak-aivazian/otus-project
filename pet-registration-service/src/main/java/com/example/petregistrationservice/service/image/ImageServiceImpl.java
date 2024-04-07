package com.example.petregistrationservice.service.image;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final String bucket;

    public ImageServiceImpl(@Value("${app.image.bucket}") String bucket) {
        this.bucket = bucket;
    }

    @SneakyThrows
    @Override
    public void upload(String imagePath, InputStream content) {
        var fullImagePath = Path.of(bucket, imagePath);
        log.info("saving image " + fullImagePath);

        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    @Override
    public Optional<byte[]> get(String imagePath) {
        var fullImagePath = Path.of(bucket, imagePath);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }
}
