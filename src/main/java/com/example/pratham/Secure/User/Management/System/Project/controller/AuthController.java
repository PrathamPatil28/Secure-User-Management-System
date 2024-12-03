package com.example.pratham.Secure.User.Management.System.Project.controller;

import com.example.pratham.Secure.User.Management.System.Project.dto.AuthRequest;
import com.example.pratham.Secure.User.Management.System.Project.dto.AuthRespones;
import com.example.pratham.Secure.User.Management.System.Project.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRespones> login (@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
                    String token= jwtUtil.generateToken(request.getUsername());
                    return ResponseEntity.ok(new AuthRespones(token));


        }catch (AuthenticationException e){
            return ResponseEntity.status(401).body(new AuthRespones("Invalid Credentials"));
        }
    }
}
