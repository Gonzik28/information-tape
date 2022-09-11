package com.gonzik28.information_tape.controller;

import com.gonzik28.information_tape.dto.RequestCommentDto;
import com.gonzik28.information_tape.dto.ResponseCommentDto;
import com.gonzik28.information_tape.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseCommentDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseCommentDto> create(@RequestBody RequestCommentDto requestCommentDto) {
        ResponseCommentDto responseCommentDto = commentService.create(requestCommentDto);
        return ResponseEntity.ok(responseCommentDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
