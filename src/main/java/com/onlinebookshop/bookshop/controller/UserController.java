package com.onlinebookshop.bookshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onlinebookshop.bookshop.entity.User;
import com.onlinebookshop.bookshop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return (user != null)
            ? ResponseEntity.ok(user)
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return (updatedUser != null)
            ? ResponseEntity.ok(updatedUser)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean deleted = userService.deleteUser(id);
        return deleted
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
