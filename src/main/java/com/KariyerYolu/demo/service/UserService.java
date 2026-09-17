package com.KariyerYolu.demo.service;
 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.KariyerYolu.demo.dto.User.UserRegisterRequest;
import com.KariyerYolu.demo.dto.User.UserResponse;
import com.KariyerYolu.demo.entity.User;
import com.KariyerYolu.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService   {

     private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // YENİ EKLENDİ
    public UserResponse registerUser(UserRegisterRequest request) {
        User user = new User();
        user.setEmail(request.email());
        
        // KRİTİK DEĞİŞİKLİK: Şifreyi veritabanına kaydetmeden önce Kriptoluyoruz!
        user.setPassword(passwordEncoder.encode(request.password())); 
        
        user.setRole(request.role());
        User savedUser = userRepository.save(user);
        
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
    }
}
