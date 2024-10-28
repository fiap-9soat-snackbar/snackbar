package com.snackbar.cooking.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snackbar.cooking.domain.Cooking;

public interface CookingRepository extends MongoRepository<Cooking, String> {

}
