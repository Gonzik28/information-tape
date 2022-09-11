package com.gonzik28.information_tape.dto.utils;

import com.gonzik28.information_tape.dto.RequestRightDto;
import com.gonzik28.information_tape.dto.ResponseRightDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.entity.RightEntity;
import com.gonzik28.information_tape.entity.UserEntity;


public class RightUtils {

    public static ResponseRightDto rightEntityToDto(
            RightEntity rightEntity) {
        ResponseRightDto responseRightDto = new ResponseRightDto();
        responseRightDto.setId(rightEntity.getId());
        responseRightDto.setUserRight(rightEntity.getUserRight());
        UserEntity userEntity = rightEntity.getUser();
        ResponseUserDto responseUserDto = UserUtils.userEntityToDto(userEntity);
        responseRightDto.setUser(responseUserDto);
        return responseRightDto;
    }

    public static RightEntity rightRequestDtoToEntity(
            RequestRightDto requestRightDto,
            UserEntity userEntity) {
        RightEntity rightEntity = new RightEntity();
        rightEntity.setUser(userEntity);
        rightEntity.setUserRight(requestRightDto.getUserRight());
        return rightEntity;
    }
}
