package com.jeiker.ssoclient1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/client")
    public String hello(Principal user) {
        return "Hello " + user.getName();
    }
}
