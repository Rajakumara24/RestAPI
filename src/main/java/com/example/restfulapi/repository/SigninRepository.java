package com.example.restfulapi.repository;

import com.example.restfulapi.model.SigninModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SigninRepository extends JpaRepository<SigninModel, String> {
    SigninModel findByEmail(String email);
    boolean existsByEmail(String email);
}
