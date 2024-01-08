package com.jwt.jwt.dtos;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginResponse {

    private String token;

    private long expiresIn;

}
