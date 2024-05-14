package com.moe.exception_handling.demo.controller;

import com.moe.exception_handling.demo.dto.AuthDto;
import com.moe.exception_handling.demo.dto.UserDto;
import com.moe.exception_handling.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Routes", description = "Authentication routes")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Operation(summary = "Process login")
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody UserDto userDto) {
        //login processing code
        AuthDto authDto = authService.processLogin(userDto);

        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }
}
