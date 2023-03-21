package com.computacao.nuvem.peoplemicroservice.model.repository;

import com.computacao.nuvem.peoplemicroservice.model.Title;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, String> {

    @Query("SELECT t FROM Title t where :genre IN t.genres AND (t.type = 'movie' OR t.type = 'tvMovie' OR t.type = 'tvSeries') ORDER BY t.startYear DESC")
    public Optional<List<Title>> getMoviesOrSeriesByGenres(@Param("genre") String genre, PageRequest of);
}
