package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.UserDto;
import com.picpay.picpaychallenge.dto.request.UserRequestDto;
import com.picpay.picpaychallenge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "document.cpf", source = "document.cpf")
    @Mapping(target = "document.cnpj", source = "document.cnpj")
    UserDto userToUserDto(User from);

    @Mapping(target = "document.cpf", source = "document.cpf")
    @Mapping(target = "document.cnpj", source = "document.cnpj")
    User userRequestDtoToUser(UserRequestDto from);

}
