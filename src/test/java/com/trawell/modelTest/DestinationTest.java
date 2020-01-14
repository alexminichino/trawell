package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;


import com.trawell.models.*;

import org.junit.Test;

/**
 * @author Paolo Fasano
 */
public class DestinationTest {

    @Test
    public void testSetGetLocation() {
        Destination instance = new Destination();
        instance.setLocation("Location");
        assertEquals("Location", instance.getLocation());
    }
   
    @Test
    public void testSetGetDescription() {
        Destination instance = new Destination();
        instance.setDescription("description");
        assertEquals("description", instance.getDescription());
    }

    @Test
    public void testSetGetIsVisited() {
        Destination instance = new Destination();
        instance.setIsVisited(true);
        assertEquals(true, instance.getIsVisited());
    }

}