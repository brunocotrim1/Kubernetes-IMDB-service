package com.computacao.nuvem.tittlesmicroservice.controller;

import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.service.LogService;
import com.computacao.nuvem.tittlesmicroservice.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TitleController {

    @Autowired
    TitleService titleService;
    @Autowired
    LogService logService;

    @GetMapping("/topHundredYear")
    public ResponseEntity<?> topHundredYear(@RequestParam("year") int year) {
        logService.info("Search for top Hundred by Year was Made where year = " + year);
        return titleService.top100MoviesByYear(year);
    }

    @GetMapping("/byGenre")
    public ResponseEntity<?> bygenre(@RequestParam("page") int page, @RequestParam("genre") String genre) {
        logService.info("Search for genre was Made where genre = " + genre + " and page = " + page);
        return titleService.listByGenre(genre, page);
    }

    @PutMapping("/updateTitle")
    public ResponseEntity<?> updateTitle(@RequestBody Title title) {
        logService.info("Updating Title with value: " + title.toString());
        return titleService.manageTitles(title);
    }

    @GetMapping("/getTitles/{page}")
    public ResponseEntity<?> getTitles(@PathVariable("page") int page) {
        logService.info("Search for titles was made for page = " + page);
        return titleService.titleCatalog(page);
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> getTitles(@RequestParam("name") String name) {
        logService.info("Search by name was made with name= " + name);
        return titleService.findByName(name);
    }

    @GetMapping("/findTitleAkas")
    public ResponseEntity<?> findTitleAkas(@RequestParam("title") String titleId) {
        logService.info("Search for Akas of title with id= " + titleId);
        return titleService.findTitleAkas(titleId);
    }
}

