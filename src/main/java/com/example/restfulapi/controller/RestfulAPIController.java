package com.example.restfulapi.controller;

import com.example.restfulapi.model.RestfulAPIModel;
import com.example.restfulapi.service.RestfulAPIService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup-request")
@CrossOrigin(origins = "*")
public class RestfulAPIController {
    @Autowired
    private RestfulAPIService restfulAPIService;

    @GetMapping("/get")
    public ResponseEntity<?>  getAllUsers(){
        JSONObject resp=new JSONObject();
        try{
            List<RestfulAPIModel> restfulAPIModels=restfulAPIService.findAll();
            return new ResponseEntity<List<RestfulAPIModel>>(restfulAPIModels, HttpStatus.OK);
        }
        catch (Exception e){
            resp.put("Description:",e.getLocalizedMessage());
            resp.put("Status","failure");
            return  new ResponseEntity<String>(resp.toString(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getFacultyById(@RequestParam String email){
        JSONObject resp=new JSONObject();
        try{
            RestfulAPIModel demoModel=restfulAPIService.findByEmail(email);
            return new ResponseEntity<RestfulAPIModel>(demoModel,HttpStatus.OK);
        }
        catch (Exception e){
            resp.put("description:",e.getLocalizedMessage());
            resp.put("status","failure");
            return new ResponseEntity<String>(resp.toString(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> postFacultySignup(@RequestBody RestfulAPIModel restfulAPIModel){
        JSONObject resp=new JSONObject();
        try{
            if(restfulAPIService.exists(restfulAPIModel.getEmail())){
                return new ResponseEntity<String>("Email Id already exists",HttpStatus.CONFLICT);
            }
            RestfulAPIModel restfulAPIModel1=restfulAPIService.save(restfulAPIModel);
            return  new ResponseEntity<RestfulAPIModel>(restfulAPIModel1,HttpStatus.OK);
        }catch (Exception e){
            resp.put("description:",e.getLocalizedMessage());
            resp.put("status","failure");
            return new ResponseEntity<String>(resp.toString(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateFacultySignup(@RequestBody RestfulAPIModel restfulAPIModel) {
        JSONObject resp = new JSONObject();
        try {
            if (restfulAPIService.exists(restfulAPIModel.getEmail())) {
                RestfulAPIModel resp1 = restfulAPIService.save(restfulAPIModel);
                return new ResponseEntity<RestfulAPIModel>(resp1, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Email Not Found",HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            resp.put("description:", e.getLocalizedMessage());
            resp.put("status", "failure");
            return new ResponseEntity<String>(resp.toString(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deletefun(@RequestParam String email){
        JSONObject resp=new JSONObject();
        try{
            if(restfulAPIService.exists(email)){
                restfulAPIService.deleteByEmail(email);
            }
            return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            resp.put("description:", e.getLocalizedMessage());
            resp.put("status:","failure");
            return new ResponseEntity<String>(resp.toString(),HttpStatus.NOT_FOUND);
        }
    }

}