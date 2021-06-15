package com.example.app.controller;

import com.example.app.model.PushNotificationRequest;
import com.example.app.model.PushNotificationResponse;
import com.example.app.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/hello/{token}")
    public String hello(@PathVariable String token) {
//        String tokenDragan = "dg0e-ulVE6I:APA91bFykixSYni2Kp-D2rZwbic5lE91TGYLi4Xrc1xLVZq49J-9boBOUNO4JXB2VAxVvE2VkQmGKAIW2kmR1UUoCV_ihKTVf_O5g6QFZbj4rbHeDROpZQ1XiZTvtmdlp_ETuWuFUzqL";
        String title = "Hello";
        String body = "Hello push world!";
        try {
            pushNotificationService.sendNotificationToToken(token, title, body);
            return "Sucesfully sent message to device with token!";
        } catch (Exception e) {
            return "ERROR! " + e.getMessage();
        }
    }

    @GetMapping("/notification")
    public ResponseEntity sendSampleNotification() {
        pushNotificationService.sendSamplePushNotification();
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/topic")
    public ResponseEntity<PushNotificationResponse> sendNotification(@RequestBody PushNotificationRequest request) {
        try {
            pushNotificationService.sendPushNotificationWithoutData(request);
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<PushNotificationResponse> sendTokenNotification(@RequestBody PushNotificationRequest request) {
        try {
            pushNotificationService.sendPushNotificationToToken(request);
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/data")
    public ResponseEntity<PushNotificationResponse> sendDataNotification(@RequestBody PushNotificationRequest request) {
        try {
            pushNotificationService.sendPushNotification(request);
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/data/customdatawithtopic")
    public ResponseEntity<PushNotificationResponse> sendDataNotificationCustom(@RequestBody PushNotificationRequest request) {
        try {
            pushNotificationService.sendPushNotificationCustomDataWithTopic(request);
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/data/customdatawithtopicjson")
    public ResponseEntity<PushNotificationResponse> sendDataNotificationCustomWithSpecificJson(@RequestBody PushNotificationRequest request) {
        try {
            pushNotificationService.sendPushNotificationCustomDataWithTopicWithSpecificJson(request);
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}