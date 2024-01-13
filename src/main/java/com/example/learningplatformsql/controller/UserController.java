package com.example.learningplatformsql.controller;

import com.example.learningplatformsql.security.JWTTokenProvider;
import com.example.learningplatformsql.entity.Role;
import com.example.learningplatformsql.entity.User;
import com.example.learningplatformsql.security.UserPrincipal;
import com.example.learningplatformsql.service.UserService;
import dto.UserDto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.GeneralMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    /*private final GeneralMapper mapper;*/

    @PostMapping("/registration")
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("Creating new user: {}", user);
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto dto) {
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();
            LocalDateTime time = LocalDateTime.now();
           /* mapper.mapToUser(dto).setLastVisitDate(time);*/
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}
