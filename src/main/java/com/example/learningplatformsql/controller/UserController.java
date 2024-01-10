package com.example.learningplatformsql.controller;

import com.example.learningplatformsql.security.JWTTokenProvider;
import com.example.learningplatformsql.dto.AuthRequestDto;
import com.example.learningplatformsql.entity.Role;
import com.example.learningplatformsql.entity.User;
import com.example.learningplatformsql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto) {
        User userDetails = (User) userService.loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            Set<Role> authorities = (Set<Role>) userDetails.getAuthorities();
            String token = jwtTokenProvider.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}
