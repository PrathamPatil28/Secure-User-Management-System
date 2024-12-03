package com.example.pratham.Secure.User.Management.System.Project.controller;

import com.example.pratham.Secure.User.Management.System.Project.dto.CreateUserRequest;
import com.example.pratham.Secure.User.Management.System.Project.dto.UpdateUserRequest;
import com.example.pratham.Secure.User.Management.System.Project.dto.UserDto;
import com.example.pratham.Secure.User.Management.System.Project.model.User;
import com.example.pratham.Secure.User.Management.System.Project.services.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
   private final UserDetailsService service;

    public UserController(UserDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request){
       return ResponseEntity.ok(service.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(service.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
