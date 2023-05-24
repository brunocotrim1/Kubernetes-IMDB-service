package com.computacao.nuvem.tittlesmicroservice.service;

import com.computacao.nuvem.tittlesmicroservice.model.Aka;
import com.computacao.nuvem.tittlesmicroservice.model.CustomLogs;
import com.computacao.nuvem.tittlesmicroservice.model.Ratings;
import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.model.repository.AkaRepository;
import com.computacao.nuvem.tittlesmicroservice.model.repository.RatingsRepository;
import com.computacao.nuvem.tittlesmicroservice.model.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {
    @Autowired
    RatingsRepository ratingsRepository;
    @Autowired
    TitleRepository titleRepository;
    @Autowired
    AkaRepository akaRepository;

    public ResponseEntity<?> top100MoviesByYear(Integer year) {
        Optional<List<Ratings>> ratingsOpt = ratingsRepository.getTop100ByYear(year.toString(), PageRequest.of(0
                , 100));

        if (ratingsOpt.isPresent() && !ratingsOpt.get().isEmpty()) {
            return ResponseEntity.ok(ratingsOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> listByGenre(String genre, int page) {
        Optional<List<Title>> titleOpt = titleRepository.getMoviesOrSeriesByGenres(genre,
                PageRequest.of(page, 100));

        if (titleOpt.isPresent() && !titleOpt.get().isEmpty()) {
            return ResponseEntity.ok(titleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> manageTitles(Title title) {
        try {
            if (!titleRepository.existsById(title.getId())) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(titleRepository.save(title));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<?> titleCatalog(int pageNumber) {
        Page<Title> page = titleRepository.findAll(PageRequest.of(pageNumber, 100));
        if(page == null)
            return ResponseEntity.notFound().build();
        List<Title> titles = page.getContent();
        return titles.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(titles);
    }


    public ResponseEntity<?> findByName(String name) {
        Optional<List<Title>> titleOpt = titleRepository.findByPrimaryTitle(name);
        if (titleOpt.isPresent() && !titleOpt.get().isEmpty()) {
            return ResponseEntity.ok(titleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> findTitleAkas(String titleId) {
        Optional<List<Aka>> akaOpt = akaRepository.getByTitleId(titleId);
        if (akaOpt.isPresent() && !akaOpt.get().isEmpty()) {
            return ResponseEntity.ok(akaOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
