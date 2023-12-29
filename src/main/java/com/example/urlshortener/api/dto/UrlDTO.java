package com.example.urlshortener.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlDTO(
        @JsonProperty("url_original") String original,
        @JsonProperty("url_shortened") String shortened
) {
}