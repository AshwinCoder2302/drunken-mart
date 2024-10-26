package com.drunkenmart.controller;

import com.drunkenmart.dto.UserCreateRequestDTO;
import com.drunkenmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String saveUser(@RequestBody UserCreateRequestDTO userRequestDTO) throws InvocationTargetException, IllegalAccessException {
        return userService.saveUser(userRequestDTO);
    }
}
