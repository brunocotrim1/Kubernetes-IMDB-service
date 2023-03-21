package com.computacao.nuvem.tittlesmicroservice.model.repository;

import com.computacao.nuvem.tittlesmicroservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
