package com.picpay.picpaychallenge.client;

import com.picpay.picpaychallenge.client.response.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "notification", url = "${client.notification.host}")
public interface NotificationClient {

    @GetMapping(path = "${client.notification.path}")
    NotificationResponse send();

}
