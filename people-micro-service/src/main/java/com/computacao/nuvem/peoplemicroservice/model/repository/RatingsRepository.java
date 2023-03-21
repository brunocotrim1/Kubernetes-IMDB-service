package com.computacao.nuvem.peoplemicroservice.model.repository;

import com.computacao.nuvem.peoplemicroservice.model.Ratings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingsRepository extends JpaRepository<Ratings, String> {

    @Query("SELECT r FROM Ratings r where r.title.startYear = :year AND (r.title.type = 'movie' OR r.title.type = 'tvMovie') ORDER BY r.averageRating DESC")
    public Optional<List<Ratings>> getTop100ByYear(@Param("year") String year, PageRequest of);

}
