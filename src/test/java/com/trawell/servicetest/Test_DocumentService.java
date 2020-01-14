package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.trawell.models.Document;
import com.trawell.repositories.DocumentRepository;
import com.trawell.services.DocumentService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_DocumentService {
    @InjectMocks
    DocumentService dao;
    
    @Mock
    DocumentRepository repo;

    Document newDocument;
    Document persistDocument;
    Document document;

    @Before public void init () {
        newDocument = new Document ();
        document = new Document();
        persistDocument = new Document ();
        String newDocDate = String.format("%d-%02d-%02d", 2019, 2, 21);
        String docDate = String.format("%d-%02d-%02d", 2019, 2, 22);

        document.setIdUser(1L);
        document.setId(1L);
        document.setDueDate(java.sql.Date.valueOf(docDate));
        document.setName("biglietto");
        document.setPath("/upload/documents/92908C781853A92BE9A963319F18A3C5/biglietto.pdf");

        persistDocument.setIdUser(1L);
        persistDocument.setId(1L);
        persistDocument.setDueDate(java.sql.Date.valueOf(newDocDate));
        persistDocument.setName("biglietto");
        persistDocument.setPath("/upload/documents/92908C781853A92BE9A963319F18A3C5/biglietto.pdf");

        newDocument.setIdUser(1L);
        newDocument.setDueDate(java.sql.Date.valueOf(newDocDate));
        newDocument.setName("biglietto");
        newDocument.setPath("/upload/documents/92908C781853A92BE9A963319F18A3C5/biglietto.pdf");
    }

    @Test
    public void tc_1 () {

        when(repo.save(any(Document.class))).thenReturn(persistDocument);

        assertEquals(Long.valueOf(1L) ,dao.create(newDocument).getId());
    }

    @Test
    public void tc_2 () {
        assertEquals(null, dao.create(persistDocument));
    }

    @Test
    public void tc_3 () {

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(persistDocument));
        when(repo.save(any(Document.class))).thenReturn(document);

        assertEquals(document ,dao.update(document));
    }

    @Test
    public void tc_4() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());
        assertEquals(null ,dao.update(document));
    }

    @Test
    public void tc_5() {
        ArrayList<Document> list = new ArrayList<Document>();
        list.add(document);

        when(repo.findAll()).thenReturn(list);
        Collection<Document> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(document));
    }

    @Test
    public void tc_6() {
        dao.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(document.getId());
    }
}