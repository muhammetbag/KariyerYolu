package com.KariyerYolu.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KariyerYolu.demo.dto.Auth.AuthRequest;
import com.KariyerYolu.demo.dto.Auth.AuthResponse;
import com.KariyerYolu.demo.repository.UserRepository;
import com.KariyerYolu.demo.security.JwtService;

 
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin (origins = "*")
@RestController 
@RequestMapping ("/api/auth")
@RequiredArgsConstructor 
public class AuthController {

     private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        
                // 1. Kullanıcı adı ve şifreyi doğrula (Eğer yanlışsa Spring anında 403 Hata fırlatır, aşağıya inmez)

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
                // 2. Şifre doğruysa kullanıcıyı veritabanından getir

        var user = userRepository.findByEmail(request.email()).orElseThrow();
        // 3. Kullanıcıyı doğruladıktan sonra JWT Token oluştur
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwtToken));


    }
    

}
