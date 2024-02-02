package com.picpay.picpaychallenge.service.client;

import com.picpay.picpaychallenge.client.AuthorizerClient;
import com.picpay.picpaychallenge.client.response.AuthorizerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizerService {

    private static final String SUCCESS_MESSAGE = "Autorizado";

    private final AuthorizerClient authorizerClient;

    public boolean isAuthorizedTransaction() {
        AuthorizerResponse clientResponse = authorizerClient.authorize();
        return clientResponse.message().contentEquals(SUCCESS_MESSAGE);
    }

}
