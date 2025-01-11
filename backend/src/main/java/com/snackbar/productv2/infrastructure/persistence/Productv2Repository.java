package com.snackbar.productv2.infrastructure.persistence;

// This should be equivalent to the previous ProductRepositoryImpl, therefore actually having 
// dependencies to a specific framework or library - in this case both Spring and MongoDB.

import com.snackbar.productv2.domain.entity.Productv2;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface Productv2Repository extends MongoRepository<Productv2Entity, String> {
    List<Productv2> findByCategory(String category);
    Optional<Productv2> findByName(String name);
}
