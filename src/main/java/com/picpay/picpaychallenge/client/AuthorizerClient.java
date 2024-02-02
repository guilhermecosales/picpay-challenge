package com.picpay.picpaychallenge.client;

import com.picpay.picpaychallenge.client.response.AuthorizerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "authorizer", url = "${client.authorizer.host}")
public interface AuthorizerClient {

    @GetMapping(path = "${client.authorizer.path}")
    AuthorizerResponse authorize();

}
