package com.example.user;

import com.example.user.model.Address;
import com.example.user.model.Users;
import com.example.user.repository.AddressRepo;
import com.example.user.repository.UserRepo;
import com.example.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserApplicationTests {

    @Mock
    private UserRepo userRepo;

    @Mock
    private AddressRepo addressRepo;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Should Get All User Data")
    public void testOnGettingAllUsers() {
        List<Users> usersList = new ArrayList<>();
        Address address1= new Address(1,"address1","city1","state1","1");
        Users user1 = new Users(1,"user1","user1","user1@user1","12-01-2024","male",address1);
        Address address2 = new Address(2,"address2","city2","state2","2");
        Users user2 = new Users(2,"user2","user2","user2@user2","12-01-2024","male",address2);
        usersList.add(user1);
        usersList.add(user2);

        when(userRepo.findAll()).thenReturn(usersList);

        ResponseEntity<List<Users>> responseEntity = userService.getAlluser();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usersList, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should Get User Data By Id")
    public void testOnGetUserById() {
        int userId=1;
        Users user = new Users();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<Users> responseEntity = userService.getUserById(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should Add New User Data")
    public void testOnAddingNewUser() {
        Address address1= new Address(1,"address1","city1","state1","1");
        Users user1 = new Users(1,"user1","user1","user1@user1","12-01-2024","male",address1);

        when(userRepo.save(user1)).thenReturn(user1);

        ResponseEntity<Users> responseEntity = userService.addNewUser(user1);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user1, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should Update User Data")
    public void testOnUpdateUserData() {
        Address address1= new Address(1,"address1","city1","state1","1");
        Users user1 = new Users(1,"user1","user1","user1@user1","12-01-2024","male",address1);

        when(userRepo.save(user1)).thenReturn(user1);

        ResponseEntity<Users> responseEntity = userService.updateUserData(user1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user1, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should Deleting User Data By Id")
    public void testOnDeletingUserData() {
        int userId=1;
        Users user = new Users();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        userRepo.deleteById(userId);

        ResponseEntity<Void> responseEntity = userService.deleteUserData(userId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
