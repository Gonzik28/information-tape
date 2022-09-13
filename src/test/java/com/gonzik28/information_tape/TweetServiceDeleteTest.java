package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestTweetDto;
import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseTweetDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.service.TweetService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
public class TweetServiceDeleteTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @SpyBean
    private TweetRepository tweetRepository;

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

        ResponseTweetDto tweet = tweetService.create(requestTweetDto);

        boolean isExistBeforeDelete = tweetRepository.findById(tweet.getId()).isPresent();

        tweetService.delete(tweet.getId());

        boolean isExistAfterDelete = tweetRepository.findById(tweet.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}
