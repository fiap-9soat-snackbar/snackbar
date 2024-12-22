package com.snackbar.iam.useCase;

import com.snackbar.iam.application.JwtService;
import com.snackbar.iam.entity.IamRole;
import com.snackbar.iam.entity.UserDetailsEntity;
import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.gateway.IamRepository;
import com.snackbar.iam.presentation.dto.LoginResponse;
import com.snackbar.iam.presentation.dto.LoginUserDto;
import com.snackbar.iam.presentation.dto.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final JwtService jwtService;
    private final IamRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationUseCaseImpl(
            JwtService jwtService,
            IamRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(RegisterUserDto registerUserDto) {
        UserEntity user = UserEntity.builder()
                .name(registerUserDto.getFullName())
                .email(registerUserDto.getEmail())
                .role(IamRole.valueOf(registerUserDto.getRole()))
                .cpf(registerUserDto.getCpf())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public LoginResponse authenticate(LoginUserDto loginUserDto) {
        String jwtToken;
        UserDetailsEntity authenticatedUser;

        if (loginUserDto.getAnonymous()) {
            authenticatedUser = new UserDetailsEntity();
            jwtToken = jwtService.generateToken(authenticatedUser);
        } else {
            authenticatedUser = this.authenticateManager(loginUserDto);
            jwtToken = jwtService.generateToken(authenticatedUser);
        }

        return new LoginResponse(jwtToken, jwtService.getExpirationTime());
    }

    private UserDetailsEntity authenticateManager(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCpf(),
                        input.getPassword()
                )
        );

        return userRepository.findByCpf(input.getCpf())
                .orElseThrow();
    }
}
