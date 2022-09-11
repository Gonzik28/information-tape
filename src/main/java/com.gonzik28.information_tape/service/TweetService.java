package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.dto.RequestTweetDto;
import com.gonzik28.information_tape.dto.ResponseTweetDto;
import com.gonzik28.information_tape.dto.utils.TweetUtils;
import com.gonzik28.information_tape.entity.TweetEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.TweetRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseTweetDto findById(String id) {
        TweetEntity tweetEntity = tweetRepository.findById(id).get();
        return TweetUtils.tweetEntityToDto(tweetEntity);
    }

    public ResponseTweetDto create(RequestTweetDto requestTweetDto) {
        String userId = requestTweetDto.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();
        TweetEntity tweetEntity = TweetUtils.tweetRequestDtoToEntity(requestTweetDto, userEntity);
        tweetEntity.setId(UUID.randomUUID().toString());
        tweetEntity = tweetRepository.save(tweetEntity);
        return TweetUtils.tweetEntityToDto(tweetEntity);
    }

    public void delete(String id) {
        tweetRepository.deleteById(id);
    }
}
