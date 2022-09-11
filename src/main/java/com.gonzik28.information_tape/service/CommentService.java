package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.dto.RequestCommentDto;
import com.gonzik28.information_tape.dto.ResponseCommentDto;
import com.gonzik28.information_tape.dto.utils.CommentUtils;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.CommentRepository;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseCommentDto findById(String id) {
        CommentEntity commentEntity = commentRepository.findById(id).get();
        return CommentUtils.commentEntityToDto(commentEntity);
    }

    public ResponseCommentDto create(RequestCommentDto requestCommentDto) {
        String userId = requestCommentDto.getUserId();
        String tweetId = requestCommentDto.getTweetId();
        UserEntity userEntity = userRepository.findById(userId).get();
        TweetEntity tweetEntity = tweetRepository.findById(tweetId).get();
        CommentEntity commentEntity =
                CommentUtils.commentRequestDtoToEntity(requestCommentDto, userEntity, tweetEntity);
        commentEntity.setId(UUID.randomUUID().toString());
        commentEntity = commentRepository.save(commentEntity);
        return CommentUtils.commentEntityToDto(commentEntity);
    }

    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}
