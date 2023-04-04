package com.computacao.nuvem.tittlesmicroservice.controller;

import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TitleController {

    @Autowired
    TitleService titleService;

    @GetMapping("/topHundredYear")
    public ResponseEntity<?> topHundredYear(@RequestParam("year") int year) {
        return titleService.top100MoviesByYear(year);
    }

    @GetMapping("/byGenre")
    public ResponseEntity<?> bygenre(@RequestParam("page") int page, @RequestParam("genre") String genre) {
        return titleService.listByGenre(genre, page);
    }

    @PutMapping("/updateTitle")
    public ResponseEntity<?> updateTitle(@RequestBody Title title) {
        return titleService.manageTitles(title);
    }

    @GetMapping("/getTitles/{page}")
    public ResponseEntity<?> getTitles(@PathVariable("page") int page) {
        return titleService.titleCatalog(page);
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> getTitles(@RequestParam("name") String name) {
        return titleService.findByName(name);
    }

    @GetMapping("/findTitleAkas")
    public ResponseEntity<?> findTitleAkas(@RequestParam("title") String titleId) {
        return titleService.findTitleAkas(titleId);
    }
}

