package com.computacao.nuvem.peoplemicroservice.service;

import com.computacao.nuvem.peoplemicroservice.configuration.UrlConfig;
import com.computacao.nuvem.peoplemicroservice.model.Crew;
import com.computacao.nuvem.peoplemicroservice.model.Person;
import com.computacao.nuvem.peoplemicroservice.model.repository.CrewRepository;
import com.computacao.nuvem.peoplemicroservice.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    UrlConfig urlConfig;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CrewRepository crewRepository;

    public ResponseEntity<?> managePerson(Person person) {
        try {
            if (!personRepository.existsById(person.getId())) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(personRepository.save(person));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<?> findByPrimaryName(String name) {
        Optional<List<Person>> personOpt = personRepository.findByPrimaryName(name);
        if (personOpt.isPresent() && !personOpt.get().isEmpty()) {
            return ResponseEntity.ok(personOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> findByYear(String birthYear, int page) {
        Optional<List<Person>> personOpt = personRepository.findByBirthYear(birthYear,
                PageRequest.of(page, 100));

        if (personOpt.isPresent() && !personOpt.get().isEmpty()) {
            return ResponseEntity.ok(personOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getCrewByTitle(String titleid) {
        Optional<Crew> crew = crewRepository.findById(titleid);
        if (crew.isPresent()) {
            List<Person> crewElements = new ArrayList<>();
            for(String directorId : crew.get().getDirectors()){
                Optional<Person> person = personRepository.findById(directorId);
                if(person.isPresent()){
                    crewElements.add(person.get());
                }
            }
            for(String writerId : crew.get().getWriters()){
                Optional<Person> person = personRepository.findById(writerId);
                if(person.isPresent()){
                    crewElements.add(person.get());
                }
            }
            return ResponseEntity.ok(crewElements);
        }
        return ResponseEntity.notFound().build();
    }
}
