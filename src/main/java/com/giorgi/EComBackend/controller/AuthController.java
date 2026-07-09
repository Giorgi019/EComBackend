package com.giorgi.EComBackend.controller;

import com.giorgi.EComBackend.config.JwtUtil;
import com.giorgi.EComBackend.model.User;
import com.giorgi.EComBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(u -> u.email.equals(email))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.password)) {

                String token = jwtUtil.generateToken(email);

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("message", "ავტორიზაცია წარმატებულია!");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "იმეილი ან პაროლი არასწორია!");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}