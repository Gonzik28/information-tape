package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.*;
import com.gonzik28.information_tape.repository.CommentRepository;
import com.gonzik28.information_tape.service.CommentService;
import com.gonzik28.information_tape.service.TweetService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class CommentServiceDeleteTest {
    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private CommentService commentService;

    @SpyBean
    private CommentRepository commentRepository;

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

        ResponseCommentDto comment = commentService.create(requestCommentDto);

        boolean isExistBeforeDelete = commentRepository.findById(comment.getId()).isPresent();

        commentService.delete(comment.getId());

        boolean isExistAfterDelete = commentRepository.findById(comment.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}
