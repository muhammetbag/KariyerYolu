package com.KariyerYolu.demo.service;
 
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

    public UserResponse registerUser(UserRegisterRequest request) {
        // Check if the email is already registered
        if (!request.password().equals(request.passwordConfirm())) {
            throw new RuntimeException("Hata: Şifreler birbiriyle uyuşmuyor!");
        }
        // Kural 2: Bu e-posta ile kayıtlı biri var mı?
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Hata: Bu email adresi zaten kullanımda!");
        }
         User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());  
        newUser.setRole(request.role());
         User savedUser = userRepository.save(newUser);
        UserResponse response = new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
       return response;
    }
}
