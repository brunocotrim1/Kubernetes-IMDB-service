package com.computacao.nuvem.tittlesmicroservice.controller;

import com.computacao.nuvem.tittlesmicroservice.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EpisodeController {
    @Autowired
    EpisodeService episodeService;

    @GetMapping("/seriesEpisodes")
    public ResponseEntity<?> getSeriesEpisodes(@RequestParam("name") String name) {
        return episodeService.findEpisodesOfASeries(name);
    }

    @GetMapping("/getEpisodeNumber")
    public ResponseEntity<?> getEpisodeNumber(@RequestParam("episodeId") String episodeId) {
        return episodeService.getEpisodeNumber(episodeId);
    }

    @GetMapping("/getSeasonNumber")
    public ResponseEntity<?> getSeasonNumber(@RequestParam("episodeId") String episodeId) {
        return episodeService.getSeasonNumber(episodeId);
    }

}
