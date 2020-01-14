package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Objects;

import com.trawell.models.Ad;

import org.junit.Test;
/**
 * @author Paolo Fasano
 */
public class AdTest {

    @Test
    public void testSetAdPaymentMethod() {
        Ad instance = new Ad();
        instance.setAdPaymentMethod("adPayment");
        assertEquals("adPayment", instance.getAdPaymentMethod());
    }

    @Test
    public void testGetAdPaymentMethod() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        assertEquals("adPayment", instance.getAdPaymentMethod());
    }

    @Test
    public void testSetAdStartingDate() {
        Ad instance = new Ad();
        instance.setAdStartingDate(new Date(0, 0, 0));
        assertEquals(new Date(0, 0, 0), instance.getAdStartingDate());
    }

    @Test
    public void testGetAdStartingDate() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        assertEquals(new Date(0, 0, 0), instance.getAdStartingDate());
    }

    @Test
    public void testSetAdDueDate() {
        Ad instance = new Ad();
        instance.setAdDueDate(new Date(0, 0, 0));
        assertEquals(new Date(0, 0, 0), instance.getAdDueDate());
    }

    @Test
    public void testGetAdDueDate() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        assertEquals(new Date(0, 0, 0), instance.getAdDueDate());
    }

    @Test
    public void testSetIdPhoto() {
        Ad instance = new Ad();
        instance.setIdPhoto("0");
        assertEquals("0", instance.getIdPhoto());
    }

    @Test
    public void testGetIdPhoto() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        assertEquals("0", instance.getIdPhoto());
    }

    @Test
    public void testToString() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        String answere = "{ id='1', idOwner='0', adPaymentMethod='adPayment', adCost='100', adStartingDate='1899-12-31', adDueDate='1899-12-31', idPhoto='0'}";
        assertEquals(answere, instance.toString());
    }

    /*
    @Test
    public void testHashcode() {
        Date adStartingDate = new Date(0, 0, 0);
        Date adDueDate = new Date(0, 0, 0);
        Ad instance = new Ad(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        int answere = Objects.hash(1L, 0L, "adPayment", 100L, adStartingDate, adDueDate, "0");
        assertEquals(answere, instance.hashCode());
    }
*/

}
