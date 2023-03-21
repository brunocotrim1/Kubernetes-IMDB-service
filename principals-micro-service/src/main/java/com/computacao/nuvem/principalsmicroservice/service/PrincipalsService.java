package com.computacao.nuvem.principalsmicroservice.service;

import com.computacao.nuvem.principalsmicroservice.model.Person;
import com.computacao.nuvem.principalsmicroservice.model.Principal;
import com.computacao.nuvem.principalsmicroservice.model.Ratings;
import com.computacao.nuvem.principalsmicroservice.model.Title;
import com.computacao.nuvem.principalsmicroservice.model.repository.PersonRepository;
import com.computacao.nuvem.principalsmicroservice.model.repository.PrincipalsRepository;
import com.computacao.nuvem.principalsmicroservice.model.repository.RatingsRepository;
import com.computacao.nuvem.principalsmicroservice.model.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrincipalsService {

    @Autowired
    PrincipalsRepository principalsRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TitleRepository titleRepository;


    public ResponseEntity<?> getTitleByActor(String actorId, int page) {
        Optional<List<String>> titleIdListOpt = principalsRepository.getTitlesByActorId(actorId,
                PageRequest.of(page, 100));
        if (titleIdListOpt.isPresent() && !titleIdListOpt.get().isEmpty()) {
            List<Title> titleList = new ArrayList<>();
            for (String titleId : titleIdListOpt.get()){
                Optional<Title> titleOpt = titleRepository.findById(titleId);
                if (titleOpt.isPresent()) {
                    titleList.add(titleOpt.get());
                }
            }
            return ResponseEntity.ok(titleList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<?> getCharactersByActorTitle(String actorId, String titleId) {
        Optional<List<String>> characterList = principalsRepository.getCharactersByActorTitle(actorId, titleId);
        if (characterList.isPresent() && !characterList.get().isEmpty()) {
            return ResponseEntity.ok(characterList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getActorsOfTitle(String titleId) {
        Optional<List<String>> principalList = principalsRepository.getActorsOfTitle(titleId);
        if (principalList.isPresent() && !principalList.get().isEmpty()) {
            List<Person> actors = new ArrayList<>();
            for(String principal : principalList.get()){
                Optional<Person> actorPerson = personRepository.findById(principal);
                if (actorPerson.isPresent()){
                    actors.add(actorPerson.get());
                }
            }
            return ResponseEntity.ok(actors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
