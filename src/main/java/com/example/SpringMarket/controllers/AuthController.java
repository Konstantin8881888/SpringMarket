package com.example.SpringMarket.controllers;

import com.example.SpringMarket.dtos.JwtRequest;
import com.example.SpringMarket.dtos.JwtResponse;
import com.example.SpringMarket.dtos.StringResponse;
import com.example.SpringMarket.services.UserService;
import com.example.SpringMarket.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController
{
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest)
    {
        try
        {
            System.out.println("Received username: " + authRequest.getUsername());
            System.out.println("Received password: " + authRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            System.out.println("tertre");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus)
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/auth_check")
    public StringResponse authCheck(Principal principal)
    {
        return new StringResponse(principal.getName());
    }

//    @GetMapping("/secured")
//    public String helloSecurity()
//    {
//        return "Hello";
//    }
}
