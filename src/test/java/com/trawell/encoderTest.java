package com.trawell;

import static org.junit.Assert.assertEquals;

import com.trawell.utilities.Encoder;

import org.junit.Test;

public class encoderTest {

    @Test
    public void testSalt() {
       Encoder instance = new Encoder();
        String test = instance.encoding("salt", 4);
        assertEquals("E2C7CD02D7F32E16AABB7E6D743B32FE", instance.encoding("salt", 4));
    }

   
}