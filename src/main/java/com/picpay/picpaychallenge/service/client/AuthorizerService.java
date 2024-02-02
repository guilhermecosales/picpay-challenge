package com.picpay.picpaychallenge.service.client;

import com.picpay.picpaychallenge.client.AuthorizerClient;
import com.picpay.picpaychallenge.client.response.AuthorizerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizerService {

    private static final String SUCCESS_MESSAGE = "Autorizado";

    private final AuthorizerClient authorizerClient;

    public boolean isAuthorizedTransaction(Long payerId, Long payeeId, BigDecimal amount) {
        AuthorizerResponse clientResponse = authorizerClient.authorize();

        if (StringUtils.hasText(SUCCESS_MESSAGE)) {
            log.info("Authorized transaction.");
            log.info("Payer: {}, Payee: {} and Amount: {}", payerId, payeeId, amount);
        }

        return clientResponse.message().contentEquals(SUCCESS_MESSAGE);
    }

}
