package com.computacao.nuvem.tittlesmicroservice.service;

import com.computacao.nuvem.tittlesmicroservice.configuration.UrlConfig;
import com.computacao.nuvem.tittlesmicroservice.model.Episode;
import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.model.objects.SeasonDescription;
import com.computacao.nuvem.tittlesmicroservice.model.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService {
    @Autowired
    UrlConfig urlConfig;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    EpisodeRepository episodeRepository;

    public ResponseEntity<?> findEpisodesOfASeries(String name) {
        try {
            ResponseEntity<List<Title>> response = restTemplate.exchange(urlConfig.getTitleMicroService() +
                    "/findByName?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Title>>() {
            }, name);


            if (response.getBody() == null || response.getBody().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            List<SeasonDescription> responseBody = new ArrayList<>();
            for (Title t : response.getBody()) {
                Optional<List<Episode>> episodesOpt = episodeRepository.findByParentTconstOrderBySeasonNumberAscEpisodeNumberAsc(t.getId());
                if (episodesOpt.isPresent() && !episodesOpt.get().isEmpty()) {
                    responseBody.add(SeasonDescription.builder().parentTitle(t).episodes(episodesOpt.get()).build());
                }
            }
            return responseBody == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<?> getEpisodeNumber(String episodeId) {
        Optional<Episode> episode = episodeRepository.findById(episodeId);
        if (episode.isPresent()) {
            return ResponseEntity.ok(episode.get().getEpisodeNumber());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getSeasonNumber(String episodeId) {
        Optional<Episode> episode = episodeRepository.findById(episodeId);
        if (episode.isPresent()) {
            return ResponseEntity.ok(episode.get().getSeasonNumber());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
