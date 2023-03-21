package com.computacao.nuvem.peoplemicroservice.model.repository;

import com.computacao.nuvem.peoplemicroservice.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, String> {
    Optional<List<Episode>> findByParentTconstOrderBySeasonNumberAscEpisodeNumberAsc(String parentTconst);
}
