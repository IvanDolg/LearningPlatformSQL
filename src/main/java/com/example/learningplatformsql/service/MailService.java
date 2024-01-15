package com.example.learningplatformsql.service;

import com.example.learningplatformsql.entity.User;
import com.example.learningplatformsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordByEmail(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String password = user.getPassword();
            String recipientEmail = user.getEmail();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipientEmail);
            message.setSubject("Your password");
            message.setText("Your password: " + password);
            mailSender.send(message);
        }
    }
}
