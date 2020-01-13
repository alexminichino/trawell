package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.trawell.models.Carsharing;
import com.trawell.models.Complaint;
import com.trawell.models.User;
import com.trawell.repositories.ComplaintRepository;
import com.trawell.services.ComplaintService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_ComplaintService {
    @InjectMocks
    ComplaintService dao;
    
    @Mock
    ComplaintRepository repo;

    Complaint newComplaint;
    Complaint complaint;

    @Before public void init () {
        newComplaint = new Complaint ();
        complaint = new Complaint();

        complaint.setIdUser(1L);
        complaint.setId(1L);
        complaint.setComplaintAnswere("Deal with it");
        complaint.setComplaintAnswered(true);
        complaint.setComplaintDescription("I don't like this post");
        complaint.setComplaintObject("Dislike");
        complaint.setMail("mario@gmail.com");

        newComplaint.setIdUser(1L);
        newComplaint.setComplaintDescription("I don't like this post");
        newComplaint.setComplaintObject("Dislike");
        newComplaint.setMail("mario@gmail.com");
    }

    @Test
    public void tc_1 () {
        Complaint savedComplaint = new Complaint();
        savedComplaint.setIdUser(1L);
        savedComplaint.setId(1L);
        savedComplaint.setComplaintAnswere("Deal with it");
        savedComplaint.setComplaintAnswered(true);
        savedComplaint.setComplaintDescription("I don't like this post");
        savedComplaint.setComplaintObject("Dislike");
        savedComplaint.setMail("mario@gmail.com");


        when(repo.save(any(Complaint.class))).thenReturn(savedComplaint);

        assertEquals(Long.valueOf(1L) ,dao.create(newComplaint).getId());
    }

    @Test
    public void tc_2 () {
        assertEquals(null, dao.create(complaint));
    }

    @Test
    public void tc_3 () {
        Complaint savedComplaint = new Complaint();

        savedComplaint.setIdUser(1L);
        savedComplaint.setId(1L);
        savedComplaint.setComplaintAnswere("Deal with it");
        savedComplaint.setComplaintAnswered(true);
        savedComplaint.setComplaintDescription("I don't like this post");
        savedComplaint.setComplaintObject("Dislike");
        savedComplaint.setMail("mario@gmail.com");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(savedComplaint));
        when(repo.save(any(Complaint.class))).thenReturn(complaint);

        assertEquals(complaint ,dao.update(complaint));
    }

    @Test
    public void tc_4() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(complaint));
    }

    @Test
    public void tc_5() {
        ArrayList<Complaint> list = new ArrayList<Complaint>();
        list.add(complaint);

        when(repo.findAll()).thenReturn(list);
        Collection<Complaint> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(complaint));
    }

    @Test
    public void tc_6() {
        when(repo.findById(1L)).thenReturn(Optional.of(complaint));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(complaint);
    }
}