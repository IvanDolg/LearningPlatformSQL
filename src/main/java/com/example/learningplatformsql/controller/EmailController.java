package com.example.learningplatformsql.controller;

import com.example.learningplatformsql.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private MailService mailService;
    @PostMapping
    public ResponseEntity<String> sendPasswordByEmail(@RequestBody String username) {
        mailService.sendPasswordByEmail(username);
        return ResponseEntity.ok("OK");
    }
}
