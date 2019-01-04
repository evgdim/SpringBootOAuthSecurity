package com.nevexis.token.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("")
    public Principal index(Principal user){
        return user;
    }
}
