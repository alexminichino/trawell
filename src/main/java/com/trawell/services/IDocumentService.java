package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Document;

/**
 * @author Ruggiero Gaetano
 */

public interface IDocumentService {

    Collection<Document> findAll();

    Document findOne(Long id);

    Document create(Document d);

    Document update(Document d);

    void delete(Long id);
}