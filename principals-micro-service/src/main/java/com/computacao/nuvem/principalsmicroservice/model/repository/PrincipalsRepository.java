package com.computacao.nuvem.principalsmicroservice.model.repository;

import com.computacao.nuvem.principalsmicroservice.model.Principal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrincipalsRepository extends JpaRepository<Principal, Principal.PrincipalId> {

    @Query("SELECT p.id.titleId FROM Principal p where :actorId = p.id.personId")
    Optional<List<String>> getTitlesByActorId(@Param("actorId") String actorId, PageRequest of);

    @Query("SELECT p.characters FROM Principal p where :actorId = p.id.personId AND :titleId = p.id.titleId")
    Optional<List<String>> getCharactersByActorTitle(@Param("actorId") String actorId, @Param("titleId") String titleId);

    @Query("SELECT p.id.personId FROM Principal p where :titleId = p.id.titleId AND (p.category = 'actress' OR p.category = 'actor')")
    Optional<List<String>> getActorsOfTitle(@Param("titleId") String titleId);
}
