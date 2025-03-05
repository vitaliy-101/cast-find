package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.auth.AuthenticationResponse;
import com.example.castfindbackend.entity.Role;
import com.example.castfindbackend.entity.Token;
import com.example.castfindbackend.entity.User;
import com.example.castfindbackend.exceptions.ExistByNicknameException;
import com.example.castfindbackend.exceptions.LoginException;
import com.example.castfindbackend.exceptions.NotFoundByNicknameException;
import com.example.castfindbackend.repository.TokenRepository;
import com.example.castfindbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private void revokeAllTokenByUseId(User user) {
        List<Token> validTokenListByUserId = tokenRepository.findAllAccessTokensByUserId(user.getId());
        if (!validTokenListByUserId.isEmpty()) {
            validTokenListByUserId.forEach(token -> {
                token.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUserId);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setAccessToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public AuthenticationResponse register(User user) {
        if (existByNickName(user.getNickname())) {
            throw new ExistByNicknameException(User.class, user.getNickname());
        }
        user.setRole(Role.TALENT);
        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        saveUserToken(jwt, user);
        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse authenticate(User request) throws LoginException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNickname(),
                            request.getPassword()
                    )
            );
        }
        catch (AuthenticationException e) {
            throw new LoginException(User.class);
        }
        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new NotFoundByNicknameException(User.class, request.getNickname()));
        String jwt = jwtService.generateToken(user);
        revokeAllTokenByUseId(user);
        saveUserToken(jwt, user);
        return new AuthenticationResponse(jwt);
    }

    public Boolean existByNickName(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}