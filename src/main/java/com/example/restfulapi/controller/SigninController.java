package com.example.restfulapi.controller;

import com.example.restfulapi.model.SigninModel;
import com.example.restfulapi.service.SigninService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("/signin-request")
@CrossOrigin(origins = "*")
public class SigninController {

    @Autowired
    private SigninService signinService;

    @Autowired
    private JavaMailSender javaMailSender;
    private String generateOTP() {
        int otp = (int)(Math.random() * 9000) + 1000; // For a 4-digit OTP
        return String.valueOf(otp);
    }

    // Method to send email
    private void sendEmail(String otp, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateUserSignin(@RequestBody SigninModel signinModel) {
        JSONObject resp = new JSONObject();
        try {
            boolean isValid = signinService.isValidUser(signinModel.getEmail(), signinModel.getPassword());
            if (isValid) {
                String otp = generateOTP();
                sendEmail(otp, signinModel.getEmail());
                // Optionally store the OTP for verification
                return new ResponseEntity<>("OTP sent to email", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            resp.put("description:", e.getLocalizedMessage());
            resp.put("status", "failure");
            return new ResponseEntity<>(resp.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/validate")
//    public ResponseEntity<?> validateUserSignin(@RequestBody SigninModel signinModel) {
//        JSONObject resp = new JSONObject();
//        try {
//            boolean isValid = signinService.isValidUser(signinModel.getEmail(), signinModel.getPassword());
//            if (isValid) {
//                return new ResponseEntity<>("User is valid", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            resp.put("description:", e.getLocalizedMessage());
//            resp.put("status", "failure");
//            return new ResponseEntity<>(resp.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
