package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.UserDto;
import com.picpay.picpaychallenge.dto.request.UserRequestDto;
import com.picpay.picpaychallenge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User from);

    User userRequestDtoToUser(UserRequestDto from);

}
