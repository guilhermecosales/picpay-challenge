package com.picpay.picpaychallenge.service.client;

import com.picpay.picpaychallenge.client.NotificationClient;
import com.picpay.picpaychallenge.client.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationClient notificationClient;

    public void sendEmail(String email) {
        NotificationResponse clientResponse = notificationClient.send();

        if (clientResponse.message()) {
            log.info("E-mail notification sent to {}", email);
        }
    }

}
