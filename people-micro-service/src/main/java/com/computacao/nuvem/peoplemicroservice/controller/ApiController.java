package com.computacao.nuvem.peoplemicroservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ApiController {

    @GetMapping("/api")
    @Hidden
    public RedirectView swagger_redirect() {
        return new RedirectView("/swagger-ui.html");
    }
}
