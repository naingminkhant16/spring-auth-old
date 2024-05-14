package com.moe.exception_handling.demo.service;

import com.moe.exception_handling.demo.dto.AuthDto;
import com.moe.exception_handling.demo.dto.UserDto;

public interface AuthService {
    AuthDto processLogin(UserDto userDto);
}
