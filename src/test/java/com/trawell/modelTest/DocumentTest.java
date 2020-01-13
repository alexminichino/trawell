package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import com.trawell.models.*;

import org.junit.Test;

public class DocumentTest {

    @Test
    public void testSetGetName() {
        Document instance = new Document();
        instance.setName("Name");
        assertEquals("Name", instance.getName());
    }

    @Test
    public void testSetGetPath() {
        Document instance = new Document();
        instance.setPath("Path");
        assertEquals("Path", instance.getPath());
    }

    @Test
    public void testSetGetDueDate() {
        Document instance = new Document();
        instance.setDueDate(new Date(0, 0, 0));
        assertEquals(new Date(0, 0, 0), instance.getDueDate());
    }
}