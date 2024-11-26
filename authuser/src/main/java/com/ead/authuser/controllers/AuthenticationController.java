package com.ead.authuser.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.services.UserService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {
    
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        var userSave = userService.save(userDto);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(userSave.getId())
        .toUri();

    return ResponseEntity.created(location).body(userSave);
    }
    
}
