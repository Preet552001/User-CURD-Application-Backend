package com.example.user.controller;

import com.example.user.model.Users;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserApiController {

    @Autowired
    UserService userserv;

    @GetMapping (produces = {"application/json"})
    public ResponseEntity<List<Users>> getUsers(){
        return userserv.getAlluser();
    }

    @GetMapping (path = "{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") int id){
        return userserv.getUserById(id);
    }

    @PostMapping()
    public ResponseEntity<Users> postUser(@RequestBody Users user) {
        return userserv.addNewUser(user);
    }


    @PutMapping ()
    public ResponseEntity<Users> putUser(@RequestBody Users user){
        return userserv.updateUserData(user);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable("id") int id) {
        return userserv.deleteUserData(id);
    }
}
