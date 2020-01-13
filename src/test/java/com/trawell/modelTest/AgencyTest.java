package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;


import com.trawell.models.*;

import org.junit.Test;

public class AgencyTest {

    @Test
    public void testSetGetNameAgency() {
        Agency instance = new Agency();
        instance.setNameAgency("agencyName");
        assertEquals("agencyName", instance.getNameAgency());
    }
   
    @Test
    public void testSetGetUrl() {
        Agency instance = new Agency();
        instance.setUrl("Url");
        assertEquals("Url", instance.getUrl());
    }

    @Test
    public void testSetGetVat() {
        Agency instance = new Agency();
        instance.setVat("Vat");
        assertEquals("Vat", instance.getVat());
    }

}