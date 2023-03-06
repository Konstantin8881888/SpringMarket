package com.example.SpringMarket.auth.controllers;

import com.example.SpringMarket.api.RegistrationUserDto;
import com.example.SpringMarket.auth.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringMarket.api.AppError;
import com.example.SpringMarket.api.JwtRequest;
import com.example.SpringMarket.api.JwtResponse;
import com.example.SpringMarket.auth.services.UserService;
import com.example.SpringMarket.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final String PASSWORDS_DO_NOT_MATCH_ERROR = "Пароли не совпадают";
    private static final String USER_ALREADY_EXISTS_ERROR = "Пользователь с таким именем уже существует";
    private static final String INCORRECT_CREDENTIALS_ERROR = "Неверные учетные данные";
    private static final int UNAUTHORIZED_STATUS_CODE = 401;
    private static final int BAD_REQUEST_STATUS_CODE = 400;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppError(UNAUTHORIZED_STATUS_CODE, INCORRECT_CREDENTIALS_ERROR));
        }
        return ResponseEntity.ok(generateJwtToken(authRequest.getUsername()));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppError(BAD_REQUEST_STATUS_CODE, PASSWORDS_DO_NOT_MATCH_ERROR));
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppError(BAD_REQUEST_STATUS_CODE, USER_ALREADY_EXISTS_ERROR));
        }
        User user = new User();
        user.setEmail(registrationUserDto.getEmail());
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        userService.createUser(user);

        return ResponseEntity.ok(generateJwtToken(registrationUserDto.getUsername()));
    }

    private JwtResponse generateJwtToken(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
