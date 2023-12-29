package com.example.urlshortener.persistence.repository;

import com.example.urlshortener.persistence.document.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UrlRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Url save(Url url) {
        return mongoTemplate.save(url, Url.COLLECTION);
    }

    public Optional<Url> findByUrlOriginal(String urlOriginal) {
        Url url = mongoTemplate.findOne(Query.query(Criteria.where("url_original").is(urlOriginal)), Url.class);
        return Optional.ofNullable(url);
    }

    public Optional<Url> findByUrlShortened(String shortened) {
        Url url = mongoTemplate.findOne(Query.query(Criteria.where("url_shortened").is(shortened)), Url.class);
        return Optional.ofNullable(url);
    }
}
