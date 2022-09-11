package com.gonzik28.information_tape.dto.utils;

import com.gonzik28.information_tape.dto.RequestCommentDto;
import com.gonzik28.information_tape.dto.ResponseCommentDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class CommentUtils {

    public static ResponseCommentDto commentEntityToDto(
            CommentEntity commentEntity) {
        ResponseCommentDto responseCommentDto = new ResponseCommentDto();
        responseCommentDto.setId(commentEntity.getId());
        responseCommentDto.setComment(commentEntity.getComment());
        responseCommentDto.setCreateTs(commentEntity.getCreateTs());
        UserEntity userEntity = commentEntity.getUser();
        ResponseUserDto responseUserDto = UserUtils.userEntityToDto(userEntity);
        responseCommentDto.setUser(responseUserDto);
        return responseCommentDto;
    }

    public static Set<ResponseCommentDto> commentEntityToDto(Set<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(CommentUtils :: commentEntityToDto)
                .collect(Collectors.toSet());
    }


    public static CommentEntity commentRequestDtoToEntity(
            RequestCommentDto requestCommentDto,
            UserEntity userEntity,
            TweetEntity tweetEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(requestCommentDto.getComment());
        commentEntity.setUser(userEntity);
        commentEntity.setTweet(tweetEntity);
        return commentEntity;
    }

}
