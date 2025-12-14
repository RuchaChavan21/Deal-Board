package com.dealboard.auth.service;

import com.dealboard.auth.dto.LoginRequest;
import com.dealboard.auth.dto.RegisterRequest;
import com.dealboard.auth.entity.AuthUser;
import com.dealboard.auth.repository.AuthUserRepository;
import com.dealboard.auth.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthUserRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(AuthUserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest request) {

        String hashedPassword = encoder.encode(request.getPassword());

        AuthUser user = new AuthUser(
                request.getEmail(),
                hashedPassword,
                request.getRole()
        );

        repository.save(user);
    }

    public String login(LoginRequest request) {

        AuthUser user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getId(), user.getRole());
    }
}
