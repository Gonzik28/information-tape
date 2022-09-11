package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.*;
import com.gonzik28.information_tape.dto.utils.CommentUtils;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.CommentRepository;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.CommentService;
import com.gonzik28.information_tape.service.TweetService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
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

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private TweetService tweetService;

    @Autowired
    @InjectMocks
    private CommentService commentAutoService;

    @SpyBean
    private CommentRepository commentAutoRepository;

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

    @Test
    public void deleteTest() {
        LocalDate birthDate = LocalDate.of(1995, 07, 17);

        RequestUserDto userTweet = new RequestUserDto();
        userTweet.setId(UUID.randomUUID().toString());
        userTweet.setFirstName("Никита");
        userTweet.setLastName("Баринов");
        userTweet.setBirthDate(birthDate);
        ResponseUserDto responseUserTweetDto = userService.create(userTweet);

        RequestUserDto userComment = new RequestUserDto();
        userComment.setId(UUID.randomUUID().toString());
        userComment.setFirstName("Игорь");
        userComment.setLastName("Живунов");
        userComment.setBirthDate(birthDate);
        ResponseUserDto responseUserCommentDto = userService.create(userComment);

        RequestTweetDto requestTweetDto = new RequestTweetDto();
        requestTweetDto.setTweet("Hello world");
        requestTweetDto.setUserId(responseUserTweetDto.getId());
        ResponseTweetDto tweet = tweetService.create(requestTweetDto);

        RequestCommentDto requestCommentDto = new RequestCommentDto();
        requestCommentDto.setUserId(responseUserCommentDto.getId());
        requestCommentDto.setTweetId(tweet.getId());
        requestCommentDto.setComment("Hi");

        ResponseCommentDto comment = commentAutoService.create(requestCommentDto);

        boolean isExistBeforeDelete = commentAutoRepository.findById(comment.getId()).isPresent();

        commentAutoService.delete(comment.getId());

        boolean isExistAfterDelete = commentAutoRepository.findById(comment.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}
