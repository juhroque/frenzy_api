package com.frenzy.carrentapi.controller;

import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterUserDTO data){
        userService.registerUser(data);
        return ResponseEntity.ok().build();
    }

}
