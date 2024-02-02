package com.picpay.picpaychallenge.controller;

import com.picpay.picpaychallenge.dto.common.UserDto;
import com.picpay.picpaychallenge.dto.request.UserRequestDto;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.mapper.UserMapper;
import com.picpay.picpaychallenge.service.UserService;
import com.picpay.picpaychallenge.utility.Location;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserRequestDto userRequest) {
        User newUser = userMapper.userRequestDtoToUser(userRequest);
        newUser = userService.save(newUser);

        final URI locationUri = Location.create(newUser.getId());

        return ResponseEntity.created(locationUri).body(userMapper.userToUserDto(newUser));
    }

}
