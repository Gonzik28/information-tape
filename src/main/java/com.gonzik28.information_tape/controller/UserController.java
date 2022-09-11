package com.gonzik28.information_tape.controller;

import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseUserDto> findById(@PathVariable String id) {
        ResponseUserDto responseUserDto = userService.findById(id);
        return ResponseEntity.ok(responseUserDto);
    }


    @PostMapping(value = "create/")
    public ResponseEntity<ResponseUserDto> create(@RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.create(requestUserDto);
        return ResponseEntity.ok(responseUserDto);
    }


    @PostMapping(value = "update/")
    public ResponseEntity<ResponseUserDto> update(Principal principal, @RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = null;
        String name = principal.getName();
        String id = requestUserDto.getId();
        String nameDto = userService.findById(id).getLastName();
        if (name.equals(nameDto)) {
            responseUserDto = userService.update(requestUserDto);
        } else {
            System.out.println("Пожалуйста авторизуйтесь");
        }
        return ResponseEntity.ok(responseUserDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseUserDto retrievePrincipal(ResponseUserDto user) {
        return user;
    }

}
