package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.request.LoginRequest; import com.freshvend.demo1.dto.response.LoginResponse;
public interface AuthService { LoginResponse login(LoginRequest request); }
