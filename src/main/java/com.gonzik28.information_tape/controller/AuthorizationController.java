package com.gonzik28.information_tape.controller;

import com.gonzik28.information_tape.dto.RequestAuthorizationDto;
import com.gonzik28.information_tape.dto.ResponseAuthorizationDto;
import com.gonzik28.information_tape.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseAuthorizationDto> findById(@PathVariable String id) {
        ResponseAuthorizationDto authorizationDto = authorizationService.findById(id);
        return ResponseEntity.ok(authorizationDto);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<ResponseAuthorizationDto> findByLoginPassword(
            @RequestParam String login,
            @RequestParam String password
    ) {
        ResponseAuthorizationDto authorizationDto = authorizationService.findByLoginPassword(login, password);
        return ResponseEntity.ok(authorizationDto);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponseAuthorizationDto> create(
            @RequestBody RequestAuthorizationDto requestAuthorizationDto
    ) {
        ResponseAuthorizationDto authorizationDto = authorizationService.create(requestAuthorizationDto);
        return ResponseEntity.ok(authorizationDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        authorizationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
