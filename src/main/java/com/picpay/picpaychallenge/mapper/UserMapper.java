package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.UserDto;
import com.picpay.picpaychallenge.dto.request.UserRequestDto;
import com.picpay.picpaychallenge.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User from);

    User userRequestDtoToUser(UserRequestDto from);

}
