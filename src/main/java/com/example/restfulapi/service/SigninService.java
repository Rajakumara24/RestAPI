package com.example.restfulapi.service;

import com.example.restfulapi.model.RestfulAPIModel;
import com.example.restfulapi.repository.RestfulAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SigninService {

    @Autowired
    private RestfulAPIRepository restfulAPIRepository;

    public boolean isValidUser(String email, String password) {
        RestfulAPIModel user = restfulAPIRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
