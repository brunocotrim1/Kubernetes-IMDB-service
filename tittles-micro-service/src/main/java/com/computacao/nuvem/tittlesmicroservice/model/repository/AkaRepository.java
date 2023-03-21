package com.computacao.nuvem.tittlesmicroservice.model.repository;

import com.computacao.nuvem.tittlesmicroservice.model.Aka;
import com.computacao.nuvem.tittlesmicroservice.model.Title;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AkaRepository extends JpaRepository<Aka, Aka.AkaID> {

    @Query("SELECT a FROM Aka a where :title = a.id.titleId")
    Optional<List<Aka>> getByTitleId(@Param("title") String title);

}
