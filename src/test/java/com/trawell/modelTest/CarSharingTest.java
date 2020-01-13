package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;


import com.trawell.models.*;

import org.junit.Test;

public class CarSharingTest {

    @Test
    public void testSetGetDescription() {
        Carsharing instance = new Carsharing();
        instance.setDescription("description");
        assertEquals("description", instance.getDescription());
    }
   
    @Test
    public void testSetGetDeparture() {
        Carsharing instance = new Carsharing();
        instance.setDeparture("Departure");
        assertEquals("Departure", instance.getDeparture());
    }

    @Test
    public void testSetGetArrival() {
        Carsharing instance = new Carsharing();
        instance.setArrival("Arrival");
        assertEquals("Arrival", instance.getArrival());
    }

    @Test
    public void testSetGetCarsharingspot() {
        Carsharing instance = new Carsharing();
        instance.setCarsharingspot(4);
        assertEquals(4, instance.getCarsharingspot());
    }

    @Test
    public void testHashcode() {
        Carsharing instance = new Carsharing();
        assertEquals(31, instance.hashCode());
    }


}