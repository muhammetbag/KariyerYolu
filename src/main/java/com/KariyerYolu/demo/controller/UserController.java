package com.KariyerYolu.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.KariyerYolu.demo.dto.User.UserRegisterRequest;
import com.KariyerYolu.demo.dto.User.UserResponse;
import com.KariyerYolu.demo.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/users")
@AllArgsConstructor 
public class UserController {


    private final UserService userService;

@PostMapping("/register")

    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {
        
        // Formu resepsiyonistten alıp, iş mantığına (Service) yolluyoruz.
        UserResponse savedUser = userService.registerUser(request);
        
        // İşlem başarılıysa kullanıcıya veriyi (ve 200 OK statüsünü) geri döndürüyoruz.
        return ResponseEntity.ok(savedUser);
    }

    
}
