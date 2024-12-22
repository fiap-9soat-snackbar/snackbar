package com.snackbar.iam.presentation;

import com.snackbar.iam.entity.UserEntity;
import com.snackbar.iam.presentation.dto.UserResponse;
import com.snackbar.iam.useCase.UserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getAll() {
        var iams = this.userUseCase.getAllUsers();
        return ResponseEntity.ok(iams);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponse> getByCpf(@PathVariable("cpf") String cpf) {
        var iams = this.userUseCase.getUserByCpf(cpf);
        return ResponseEntity.ok(iams);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
