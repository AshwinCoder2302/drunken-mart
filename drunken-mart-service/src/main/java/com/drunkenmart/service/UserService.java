package com.drunkenmart.service;

import com.drunkenmart.dto.UserCreateRequestDTO;

import java.lang.reflect.InvocationTargetException;

public interface UserService {
    String saveUser(UserCreateRequestDTO userRequestDTO) throws InvocationTargetException, IllegalAccessException;
}
