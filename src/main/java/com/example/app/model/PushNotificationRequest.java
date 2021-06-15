package com.example.app.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushNotificationRequest {
    private String title;
    private String message;
    private String topic;
    private String token;
}

