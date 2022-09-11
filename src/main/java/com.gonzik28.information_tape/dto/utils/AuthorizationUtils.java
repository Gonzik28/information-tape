package com.gonzik28.information_tape.dto.utils;

import com.gonzik28.information_tape.dto.RequestAuthorizationDto;
import com.gonzik28.information_tape.dto.ResponseAuthorizationDto;
import com.gonzik28.information_tape.entity.AuthorizationEntity;
import com.gonzik28.information_tape.entity.UserEntity;


public class AuthorizationUtils {

    private AuthorizationUtils() {
    }

    public static ResponseAuthorizationDto authorizationEntityToDto(
            AuthorizationEntity authorizationEntity) {
        ResponseAuthorizationDto responseAuthorizationDto = new ResponseAuthorizationDto();
        responseAuthorizationDto.setId(authorizationEntity.getId());
        responseAuthorizationDto.setLogin(authorizationEntity.getLogin());
        responseAuthorizationDto.setResponseUserDto(UserUtils.userEntityToDto(authorizationEntity.getUser()));
        return responseAuthorizationDto;
    }

    public static AuthorizationEntity authorizationRequestDtoToEntity(
            RequestAuthorizationDto requestAuthorizationDto, UserEntity userEntity) {
        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setLogin(requestAuthorizationDto.getLogin());
        authorizationEntity.setUser(userEntity);
        authorizationEntity.setPasswordHash(requestAuthorizationDto.getPassword());
        return authorizationEntity;
    }
}
