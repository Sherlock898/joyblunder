package com.sherlock.joyblunder.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherlock.joyblunder.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByEmail(String email);
}
