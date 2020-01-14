package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;

import org.junit.Test;

/**
 * @author Paolo Fasano
 */
public class ItineraryTest {

    @Test
    public void testSetGetName() {
        Itinerary instance = new Itinerary();
        instance.setName("Name");
        assertEquals("Name", instance.getName());
    }

    @Test
    public void testHashcode() {
        Itinerary instance = new Itinerary();
        assertEquals(31, instance.hashCode());
    }

}