package com.example.urlshortener.persistence.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

import static com.example.urlshortener.persistence.document.Url.COLLECTION;

@Document(COLLECTION)
@Data
@Builder
public class Url {

    public static final String COLLECTION = "URL";

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("url_original")
    private String urlOriginal;

    @Field("url_shortened")
    private List<String> urlShortened;
}