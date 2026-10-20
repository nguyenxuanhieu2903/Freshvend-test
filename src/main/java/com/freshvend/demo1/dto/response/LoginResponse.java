package com.freshvend.demo1.dto.response;
import com.freshvend.demo1.domain.enums.UserRole;
public record LoginResponse(String accessToken, UserRole role) {}
