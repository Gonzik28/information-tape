package com.gonzik28.information_tape.dto.utils;

import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.entity.UserEntity;

public class UserUtils {

    public static ResponseUserDto userEntityToDto(
            UserEntity userEntity) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(userEntity.getId());
        responseUserDto.setFirstName(userEntity.getFirstName());
        responseUserDto.setLastName(userEntity.getLastName());
        responseUserDto.setBirthDate(userEntity.getBirthDate());
        responseUserDto.setCreateTs(userEntity.getCreateTs());
        responseUserDto.setUpdateTs(userEntity.getUpdateTs());
        return responseUserDto;
    }

    public static UserEntity userDtoRequestToEntity(
            RequestUserDto requestUserDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(requestUserDto.getId());
        userEntity.setFirstName(requestUserDto.getFirstName());
        userEntity.setLastName(requestUserDto.getLastName());
        userEntity.setBirthDate(requestUserDto.getBirthDate());
        userEntity.setEnabled(true);
        return userEntity;
    }
}
