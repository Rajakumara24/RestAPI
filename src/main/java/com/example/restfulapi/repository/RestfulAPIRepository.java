package com.example.restfulapi.repository;

import com.example.restfulapi.model.RestfulAPIModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestfulAPIRepository extends JpaRepository<RestfulAPIModel, String> {
    RestfulAPIModel findByEmail(String email);
    boolean existsByEmail(String email);
}