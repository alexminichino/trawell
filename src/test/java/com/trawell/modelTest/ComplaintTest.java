package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.Complaint;

import org.junit.Test;

public class ComplaintTest{

    @Test
    public void testSetIdUser() {
      Complaint instance = new Complaint();
      instance.setIdUser(0);
      assertEquals(0, instance.getIdUser());
    }

    @Test
    public void testGetIdUser() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals(0, instance.getIdUser());
    }

    @Test
    public void testSetComplaintObject() {
      Complaint instance = new Complaint();
      instance.setComplaintObject("complaintObject");
      assertEquals("complaintObject", instance.getComplaintObject());
    }

    @Test
    public void testGetComplaintObject() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals("complaintObject", instance.getComplaintObject());
    }

    @Test
    public void testSetComplaintDescription() {
      Complaint instance = new Complaint();
      instance.setComplaintDescription("complaintDescription");
      assertEquals("complaintDescription", instance.getComplaintDescription());
    }

    @Test
    public void testGetComplaintDescription() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals("complaintDescription", instance.getComplaintDescription());
    }

    @Test
    public void testSetMail() {
      Complaint instance = new Complaint();
      instance.setMail("complaintMail");
      assertEquals("complaintMail", instance.getMail());
    }

    @Test
    public void testGetMail() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals("complaintMail", instance.getMail());
    }

    @Test
    public void testSetComplaintAnswered() {
      Complaint instance = new Complaint();
      instance.setComplaintAnswered(true);
      assertEquals(true, instance.getComplaintAnswered());
    }

    @Test
    public void testGetComplaintAnswered() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals(true, instance.getComplaintAnswered());
    }

    @Test
    public void testSetComplaintAnswere() {
      Complaint instance = new Complaint();
      instance.setComplaintAnswere("complaintAnswere");
      assertEquals("complaintAnswere", instance.getComplaintAnswere());
    }

    @Test
    public void testGetComplaintAnswere() {
      Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
      assertEquals("complaintAnswere", instance.getComplaintAnswere());
    }

    @Test
    public void testToString()
    {
        Complaint instance = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
        String answere = "{ id='1', idUser='0', complaintObject='complaintObject', complaintDescription='complaintDescription', complaintMail='complaintMail', idAnswerer='0', complaintAnswered='true', complaintAnswere='complaintAnswere'}";
        assertEquals(answere, instance.toString());
    }
}