package com.drunkenmart.service.impl;

import com.drunkenmart.dto.UserCreateRequestDTO;
import com.drunkenmart.entity.Users;
import com.drunkenmart.repository.UserRepository;
import com.drunkenmart.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(UserCreateRequestDTO userRequestDTO) throws InvocationTargetException, IllegalAccessException {
        Users user = new Users();
        BeanUtils.copyProperties(user, userRequestDTO);
        Users savedUser = userRepository.save(user);
        if(ObjectUtils.isEmpty(savedUser)){
            //Throw Exception
        }
        return "Saved Successfully";
    }
}
