package com.snackbar.iam.useCase;

import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.presentation.dto.UserResponse;

import java.util.List;

public interface UserUseCase {
    List<UserEntity> getAllUsers();
    UserResponse getUserByCpf(String cpf);
    void deleteUser(String id);
}
