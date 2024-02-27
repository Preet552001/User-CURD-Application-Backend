package com.example.user.service;

import com.example.user.model.Address;
import com.example.user.model.Users;
import com.example.user.repository.AddressRepo;
import com.example.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AddressRepo addressRepo;
    public ResponseEntity<List<Users>> getAlluser(){
        List <Users> usersList = userRepo.findAll();
        if(usersList.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(usersList));
    }

    public ResponseEntity<Users> getUserById(int id){
        Users users = userRepo.findById(id).orElse(new Users());
        if(users.getId()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(users));
    }

    public ResponseEntity<Users> addNewUser(Users user){
        try{
            Users userData=userRepo.save(user);
            user.getAddress().setUser(user);
            addressRepo.save(user.getAddress());
//            userRepo.getReferenceById()
            return ResponseEntity.status(HttpStatus.CREATED).body(userData);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Users> updateUserData(Users user){
        try{
            Users userData=userRepo.save(user);
            user.getAddress().setUser(user);
            addressRepo.save(user.getAddress());
            return ResponseEntity.status(HttpStatus.OK).body(userData);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteUserData(int id){
        Users user=getUserById(id).getBody();
            if (user.getId() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                try {
                    userRepo.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
    }
}
