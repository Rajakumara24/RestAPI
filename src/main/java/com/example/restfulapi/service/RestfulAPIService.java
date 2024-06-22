package com.example.restfulapi.service;

import com.example.restfulapi.model.RestfulAPIModel;
import com.example.restfulapi.repository.RestfulAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestfulAPIService {
    @Autowired
    private RestfulAPIRepository restfulAPIRepository;

    public List<RestfulAPIModel> findAll() {
        return restfulAPIRepository.findAll();
    }

    public RestfulAPIModel findByEmail(String email) {
        return restfulAPIRepository.findByEmail(email);
    }

    public RestfulAPIModel save(RestfulAPIModel restfulAPIModel){
        return restfulAPIRepository.save(restfulAPIModel);
    }
    public boolean exists(String email) {
        return restfulAPIRepository.existsByEmail(email);
    }
    public void deleteByEmail(String email){
        restfulAPIRepository.deleteById(email);
    }

    public boolean isValidUser(String email, String password) {
        RestfulAPIModel user = restfulAPIRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }


}