package com.sherlock.joyblunder.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherlock.joyblunder.models.BabyName;

@Repository
public interface BabyNameRepository extends CrudRepository<BabyName, Long>{
    BabyName findByName(String name);
}
