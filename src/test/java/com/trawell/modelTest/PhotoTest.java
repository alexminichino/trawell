package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;

import org.junit.Test;

public class PhotoTest {

    @Test
    public void testSetGetPath() {
        Photo instance = new Photo();
        instance.setPath("Path");
        assertEquals("Path", instance.getPath());
    }

    @Test
    public void testToString()
    {
        Photo instance = new Photo();
        instance.setPath("Path");
        instance.setId(0L);
        String answere = "{ id='0', path='Path'}";
        assertEquals(answere, instance.toString());
    }
}