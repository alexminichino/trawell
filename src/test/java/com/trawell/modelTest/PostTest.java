package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;

import org.junit.Test;

public class PostTest {

    @Test
    public void testSetGetIsReported() {
        Post instance = new Post();
        instance.setIsReported(true);
        assertEquals(true, instance.isReported());
    }

    @Test
    public void testSetGetPostDescription() {
        Post instance = new Post();
        instance.setPostDescription("PostDescription");
        assertEquals("PostDescription", instance.getPostDescription());
    }
}