package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import com.trawell.models.*;

import org.junit.Test;

/**
 * @author Paolo Fasano
 */
public class BanDataTest {

    @Test
    public void testSetBanUntil() {
        BanData instance = new BanData();
        instance.setBanUntil(new Date(0, 0, 0));
        assertEquals(new Date(0, 0, 0), instance.getBanUntil());
    }

    @Test
    public void testGetBanUntil() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0");
        assertEquals(new Date(0, 0, 0), instance.getBanUntil());
    }

    @Test
    public void testGetMotivation() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0");
        assertEquals("0", instance.getMotivation());
    }

    @Test
    public void testSetMotivation() {
        BanData instance = new BanData();
        instance.setMotivation("0");
        assertEquals("0", instance.getMotivation());
    }

    @Test
    public void testToString() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0");    
        String answere = "{ id='1', username='0', idAdmin='100', banUntil='1899-12-31', motivation='0'}";
        assertEquals(answere, instance.toString());
    }

    /*
    @Test
    public void testHashcode() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0");
        int answere = Objects.hash(1L, 0L, 100L, adStartingDate, "0");
        assertEquals(answere, instance.hashCode());
        
    }
    */

    @Test
    public void testEquals() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0");  
        assertEquals(true, instance.equals(instance));
    }
    @Test
    public void testEqualsf() {
        Date adStartingDate = new Date(0, 0, 0);
        BanData instance = new BanData(1L, 0L, 100L, adStartingDate, "0"); 
        BanData instancef = new BanData(0L, 0L, 100L, adStartingDate, "0");   
        assertEquals(false, instance.equals(instancef));
    }
    

}
