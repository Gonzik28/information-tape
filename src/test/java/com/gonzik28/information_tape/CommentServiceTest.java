package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestCommentDto;
import com.gonzik28.information_tape.dto.ResponseCommentDto;
import com.gonzik28.information_tape.dto.utils.CommentUtils;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.CommentRepository;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class CommentServiceTest {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TweetRepository tweetRepository;

    @Test
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();
        TweetEntity tweetEntity = new TweetEntity();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId("012");
        commentEntity.setUser(userEntity);
        commentEntity.setTweet(tweetEntity);
        commentEntity.setComment("Так себе из тебя актер");

        when(commentRepository.findById(any(String.class))).thenReturn(Optional.of(commentEntity));

        ResponseCommentDto commentTest = CommentUtils.commentEntityToDto(commentEntity);
        ResponseCommentDto comment = commentService.findById(commentTest.getId());

        assertNotNull(comment);
        assertSame("012", comment.getId());
        assertSame("Так себе из тебя актер", comment.getComment());
    }

    @Test
    public void createTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("111");
        userEntity.setEnabled(true);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setId("125");
        when(tweetRepository.findById(any(String.class))).thenReturn(Optional.of(tweetEntity));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId("012");
        commentEntity.setUser(userEntity);
        commentEntity.setTweet(tweetEntity);
        commentEntity.setComment("Так себе из тебя актер");

        when(commentRepository.save(any(CommentEntity.class))).thenReturn(commentEntity);

        RequestCommentDto requestCommentDto = new RequestCommentDto();
        requestCommentDto.setUserId(commentEntity.getUser().getId());
        requestCommentDto.setTweetId(commentEntity.getTweet().getId());
        ResponseCommentDto comment = commentService.create(requestCommentDto);

        assertNotNull(comment);
        assertSame("012", comment.getId());
        assertSame("111", comment.getUser().getId());
        assertSame("Так себе из тебя актер", comment.getComment());
    }

}
