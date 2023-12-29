package com.example.urlshortener.service;

import com.example.urlshortener.persistence.document.Url;
import com.example.urlshortener.persistence.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    public String shortenUrl(String originalUrl) {

        var shortenedUrl = "https://short.url/" + UUID.randomUUID().toString().hashCode();

        repository
                .findByUrlOriginal(originalUrl)
                .ifPresentOrElse(
                        /*ifPresent=*/
                        url -> {
                            url.getUrlShortened().add(shortenedUrl);
                            repository.save(url);
                        },
                        /*orElse=*/
                        () -> repository.save(Url.builder()
                                .urlOriginal(originalUrl)
                                .urlShortened(List.of(shortenedUrl))
                                .build())

                );

        return shortenedUrl;
    }

    public String findByShortened(String shortenedUrl) {
        return repository
                .findByUrlShortened(shortenedUrl)
                .map(Url::getUrlOriginal)
                .orElseThrow();
    }
}