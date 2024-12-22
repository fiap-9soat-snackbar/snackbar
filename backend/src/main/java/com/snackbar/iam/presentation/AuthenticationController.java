package com.snackbar.iam.presentation;

import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.presentation.dto.LoginResponse;
import com.snackbar.iam.presentation.dto.LoginUserDto;
import com.snackbar.iam.presentation.dto.RegisterUserDto;
import com.snackbar.iam.useCase.AuthenticationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationUseCase.register(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse loginResponse = authenticationUseCase.authenticate(loginUserDto);
        return ResponseEntity.ok(loginResponse);
    }
}
