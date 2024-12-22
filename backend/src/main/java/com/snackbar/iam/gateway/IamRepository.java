package com.snackbar.iam.gateway;

import com.snackbar.iam.entity.UserDetailsEntity;
import com.snackbar.iam.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IamRepository extends MongoRepository<UserEntity, String> {

    Optional<UserDetailsEntity> findByEmail(String email);

    Optional<UserDetailsEntity> findByCpf(String cpf);

}
