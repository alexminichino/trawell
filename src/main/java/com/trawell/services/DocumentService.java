package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Document;
import com.trawell.repositories.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruggiero Gaetano
 * 
 *         .
 */
@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private DocumentRepository DocumentRepository;

    @Override
    public Collection<Document> findAll() {
        ArrayList<Document> Documents = new ArrayList<>();
        DocumentRepository.findAll().forEach(Documents::add);
        return Documents;
    }

    @Override
    public Document findOne(Long id) {
        Optional<Document> Document = DocumentRepository.findById(id);
        return Document.get();
    }

    @Override
    public Document create(Document Document) {
        if (Document.getId() != null) {
            // cannot create Document with specified Id value
            return null;
        }
        Document savedDocument = DocumentRepository.save(Document);
        return savedDocument;
    }

    @Override
    public Document update(Document Document) {
        Document DocumentPersisted = findOne(Document.getId());
        if (DocumentPersisted == null) {
            // cannot find Document with specified Id value
            return null;
        }
        Document updatedDocument = DocumentRepository.save(Document);
        return updatedDocument;
    }

    @Override
    public void delete(Long id) {

        DocumentRepository.deleteById(id);

    }
}