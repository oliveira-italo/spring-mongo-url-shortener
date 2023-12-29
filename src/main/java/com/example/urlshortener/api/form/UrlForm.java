package com.example.urlshortener.api.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.URL;

public record UrlForm(
        @JsonProperty("url")
        @URL
        String urlOriginal
) {
}