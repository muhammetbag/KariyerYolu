package com.KariyerYolu.demo.dto.User;

public record UserRegisterRequest(

    String email,
    String password,
    String passwordConfirm,
    String role
) {

}
