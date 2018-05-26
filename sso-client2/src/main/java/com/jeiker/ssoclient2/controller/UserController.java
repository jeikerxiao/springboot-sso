package com.jeiker.ssoclient2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/client2")
    public Map<String, String> hello(Principal user) {
        return Collections.singletonMap("user", user.getName());
    }
}
