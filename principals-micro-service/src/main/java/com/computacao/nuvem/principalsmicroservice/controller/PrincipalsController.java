package com.computacao.nuvem.principalsmicroservice.controller;

import com.computacao.nuvem.principalsmicroservice.model.Title;
import com.computacao.nuvem.principalsmicroservice.service.PrincipalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrincipalsController {

    @Autowired
    PrincipalsService principalsService;

    @GetMapping("/getTitleByActor")
    public ResponseEntity<?> getTitleByActor(@RequestParam("page") int page, @RequestParam("actorId") String actorId) {
        return principalsService.getTitleByActor(actorId, page);
    }

    @GetMapping("/getCharactersByActorTitle")
    public ResponseEntity<?> getCharactersByActorTitle(@RequestParam("actorId") String actorId, @RequestParam("titleId") String titleId) {
        return principalsService.getCharactersByActorTitle(actorId, titleId);
    }

    @GetMapping("/getActorsOfTitle")
    public ResponseEntity<?> getActorsOfTitle(@RequestParam("titleId") String titleId) {
        return principalsService.getActorsOfTitle(titleId);
    }

}

