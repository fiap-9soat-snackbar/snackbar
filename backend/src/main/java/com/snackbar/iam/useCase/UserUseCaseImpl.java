package com.snackbar.iam.useCase;

import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.gateway.IamRepository;
import com.snackbar.iam.gateway.UserRepository;
import com.snackbar.iam.presentation.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUseCaseImpl implements UserUseCase {

    private final IamRepository iamRepository;
    private final UserRepository userRepository;

    public UserUseCaseImpl(
            IamRepository iamRepository,
            UserRepository userRepository
    ) {
        this.iamRepository = iamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();

        iamRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public UserResponse getUserByCpf(String cpf) {

        return userRepository.findByCpf(cpf).map(user ->
                UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .cpf(user.getCpf())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build()
        ).orElseThrow();
    }

    @Override
    public void deleteUser(String id) {
        iamRepository.deleteById(id);
    }
}
