package com.gonzik28.information_tape.controller;

import com.gonzik28.information_tape.dto.RequestRightDto;
import com.gonzik28.information_tape.dto.ResponseRightDto;
import com.gonzik28.information_tape.service.RightService;
import com.gonzik28.information_tape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/right")
public class RightController {

    @Autowired
    private RightService rightService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseRightDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(rightService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseRightDto> create(@RequestBody RequestRightDto requestRightDto) {
        ResponseRightDto responseRightDto = rightService.create(requestRightDto);
        return ResponseEntity.ok(responseRightDto);
    }

    @PostMapping(value = "/update/")
    public ResponseEntity<ResponseRightDto> update(Principal principal, @RequestBody RequestRightDto requestRightDto) {
        ResponseRightDto responseRightDto = null;
        String name = principal.getName();
        String userId = requestRightDto.getUserId();
        String nameDto = userService.findById(userId).getLastName();
        if (name.equals(nameDto)) {
            responseRightDto = rightService.update(requestRightDto);
        } else {
            System.out.println("Пожалуйста авторизуйтесь");
        }
        return ResponseEntity.ok(responseRightDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        rightService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
