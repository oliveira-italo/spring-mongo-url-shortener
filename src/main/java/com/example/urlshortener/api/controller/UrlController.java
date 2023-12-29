package com.example.urlshortener.api.controller;

import com.example.urlshortener.api.dto.UrlDTO;
import com.example.urlshortener.api.form.UrlForm;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlDTO> shortUrl(@RequestBody @Valid UrlForm form) {
        String shortened = service.shortenUrl(form.urlOriginal());
        return ResponseEntity.ok(new UrlDTO(form.urlOriginal(), shortened));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlDTO> findByShortened(@RequestParam String shortened) {
        String urlOriginal = service.findByShortened(shortened);
        return ResponseEntity.ok(new UrlDTO(urlOriginal, shortened));
    }
}
