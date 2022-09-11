package com.gonzik28.information_tape.controller;

import com.gonzik28.information_tape.dto.RequestTweetDto;
import com.gonzik28.information_tape.dto.ResponseTweetDto;
import com.gonzik28.information_tape.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTweetDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(tweetService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseTweetDto> create(@RequestBody RequestTweetDto requestTweetDto) {
        ResponseTweetDto responseTweetDto = tweetService.create(requestTweetDto);
        return ResponseEntity.ok(responseTweetDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tweetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
