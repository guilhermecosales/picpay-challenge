package com.picpay.picpaychallenge.utility;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class Location {

    public static URI create(Long storedObjectId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(storedObjectId)
                .toUri();
    }

}
