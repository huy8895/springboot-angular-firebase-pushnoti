package com.example.app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class FCMInitializer {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;


    @PostConstruct
    public void initialize() {

        try (InputStream inputStream = new ClassPathResource(firebaseConfigPath).getInputStream()) {

            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
            FirebaseOptions options = FirebaseOptions.builder()
                                                     .setCredentials(credentials)
                                                     .build();

            boolean isAppEmpty = FirebaseApp.getApps().isEmpty();
            if (isAppEmpty) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }


    }

}