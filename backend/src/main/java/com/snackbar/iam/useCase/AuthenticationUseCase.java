package com.snackbar.iam.useCase;

import com.snackbar.iam.entity.UserDetailsEntity;
import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.presentation.dto.LoginResponse;
import com.snackbar.iam.presentation.dto.LoginUserDto;
import com.snackbar.iam.presentation.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationUseCase {
    UserEntity register(RegisterUserDto registerUserDto);
    LoginResponse authenticate(LoginUserDto loginUserDto);
}
