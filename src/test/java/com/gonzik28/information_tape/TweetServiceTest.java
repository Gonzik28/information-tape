package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestTweetDto;
import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseTweetDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.dto.utils.TweetUtils;
import com.gonzik28.information_tape.entity.CommentEntity;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.repository.UserRepository;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;

    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private TweetService tweetAutoService;

    @SpyBean
    private TweetRepository tweetAutoRepository;

    @Test
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("111");
        userEntity.setEnabled(true);

        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setId("002");
        tweetEntity.setTweet("Вся жизнь театр");
        tweetEntity.setUser(userEntity);
        ResponseTweetDto tweetTest = TweetUtils.tweetEntityToDto(tweetEntity);

        when(tweetRepository.findById(any(String.class))).thenReturn(Optional.of(tweetEntity));

        ResponseTweetDto tweet = tweetService.findById(tweetTest.getId());

        assertNotNull(tweet);
        assertSame("002", tweet.getId());
        assertSame("Вся жизнь театр", tweet.getTweet());

    }

    @Test
    public void createTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("111");
        userEntity.setEnabled(true);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId("25");
        commentEntity.setUser(userEntity);

        Set<CommentEntity> commentEntities = new HashSet<>();
        commentEntities.add(commentEntity);

        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setId("125");
        tweetEntity.setUser(userEntity);
        tweetEntity.setTweet("Вся жизнь театр");
        tweetEntity.setCommentEntities(commentEntities);

        when(tweetRepository.save(any(TweetEntity.class))).thenReturn(tweetEntity);

        RequestTweetDto requestTweetDto = new RequestTweetDto();
        requestTweetDto.setId(tweetEntity.getId());
        requestTweetDto.setUserId(tweetEntity.getUser().getId());
        requestTweetDto.setTweet(tweetEntity.getTweet());
        ResponseTweetDto tweet = tweetService.create(requestTweetDto);

        assertNotNull(tweet);
        assertSame("125", tweet.getId());
        assertSame("111", tweet.getResponseUserDto().getId());
        assertSame("Вся жизнь театр", tweet.getTweet());
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

        RequestTweetDto requestTweetDto = new RequestTweetDto();
        requestTweetDto.setTweet("Hello world");
        requestTweetDto.setUserId(responseUserTweetDto.getId());

        ResponseTweetDto tweet = tweetAutoService.create(requestTweetDto);

        boolean isExistBeforeDelete = tweetAutoRepository.findById(tweet.getId()).isPresent();

        tweetAutoService.delete(tweet.getId());

        boolean isExistAfterDelete = tweetAutoRepository.findById(tweet.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}
