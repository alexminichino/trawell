package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;

import org.junit.Test;

/**
 * @author Paolo Fasano
 */
public class TrawellGroupTest {

    @Test
    public void testSetGetName() {
        TrawellGroup instance = new  TrawellGroup();
        instance.setName("Name");
        assertEquals("Name", instance.getName());
    }

    @Test
    public void testSetGetDescription() {
        TrawellGroup instance = new  TrawellGroup();
        instance.setDescription("description");
        assertEquals("description", instance.getDescription());
    }

    @Test
    public void testHashcode() {
        TrawellGroup instance = new TrawellGroup();
        assertEquals(31, instance.hashCode());
    }

    }