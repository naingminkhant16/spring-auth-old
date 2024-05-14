package com.moe.exception_handling.demo.service.impl;

import com.moe.exception_handling.demo.dto.AuthDto;
import com.moe.exception_handling.demo.dto.UserDto;
import com.moe.exception_handling.demo.entity.Token;
import com.moe.exception_handling.demo.entity.User;
import com.moe.exception_handling.demo.exception.LoginFailException;
import com.moe.exception_handling.demo.repository.TokenRepository;
import com.moe.exception_handling.demo.repository.UserRepository;
import com.moe.exception_handling.demo.service.AuthService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthDto processLogin(UserDto userDto) {
        logger.info("Processing Login");
        //process login
        User loginUser = userRepository.processLogin(userDto.getUsername(), userDto.getPassword());
        if (loginUser == null) {
            //login failed
            logger.info("Login Failed");
            throw new LoginFailException("Login Fail");
        }
        //generate token
        String token = UUID.randomUUID().toString();
        //save token in db
        tokenRepository.save(new Token(token));

        //login success
        UserDto loginUserDto = modelMapper.map(loginUser, UserDto.class);
        loginUserDto.setPassword(null);
        
        logger.info("Login Success");
        return new AuthDto(
                loginUserDto,
                token
        );
    }
}
