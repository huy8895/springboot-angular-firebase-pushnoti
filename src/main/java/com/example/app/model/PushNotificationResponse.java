package com.example.app.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushNotificationResponse {
    private int status;
    private String message;
}
