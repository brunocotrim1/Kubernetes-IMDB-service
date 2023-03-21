package com.computacao.nuvem.peoplemicroservice.model.repository;

import com.computacao.nuvem.peoplemicroservice.model.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<List<Person>> findByPrimaryName(String primaryName);

    Optional<List<Person>> findByBirthYear(String birthYear, PageRequest of);
}
