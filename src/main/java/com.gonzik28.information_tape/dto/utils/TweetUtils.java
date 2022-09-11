package com.gonzik28.information_tape.dto.utils;

import com.gonzik28.information_tape.dto.RequestTweetDto;
import com.gonzik28.information_tape.dto.ResponseCommentDto;
import com.gonzik28.information_tape.dto.ResponseTweetDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;

import java.util.Set;


public class TweetUtils {

    public static ResponseTweetDto tweetEntityToDto(
            TweetEntity tweetEntity) {
        ResponseTweetDto responseTweetDto = new ResponseTweetDto();
        responseTweetDto.setId(tweetEntity.getId());
        responseTweetDto.setTweet(tweetEntity.getTweet());
        responseTweetDto.setCreationTs(tweetEntity.getCreationTs());
        UserEntity userEntity = tweetEntity.getUser();
        ResponseUserDto responseUserDto = UserUtils.userEntityToDto(userEntity);
        responseTweetDto.setResponseUserDto(responseUserDto);
        Set<CommentEntity> commentEntities = tweetEntity.getCommentEntities();
        Set<ResponseCommentDto> responseCommentDtos = CommentUtils.commentEntityToDto(commentEntities);
        responseTweetDto.setComments(responseCommentDtos);
        return responseTweetDto;
    }

    public static TweetEntity tweetRequestDtoToEntity(
            RequestTweetDto requestTweetDto, UserEntity userEntity) {
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setTweet(requestTweetDto.getTweet());
        tweetEntity.setUser(userEntity);
        return tweetEntity;
    }
}
