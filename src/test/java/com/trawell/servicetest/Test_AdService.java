package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Ad;
import com.trawell.repositories.AdRepository;
import com.trawell.services.AdService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_AdService {
    @InjectMocks
    AdService dao;
    
    @Mock
    AdRepository repo;

    Ad newAd;
    Ad persistAd;
    Ad ad;

    @Before public void init () {
        String startingDate = String.format("%d-%02d-%02d", 2020, 2, 22);
        String dueDate = String.format("%d-%02d-%02d", 2020, 2, 25);
        newAd = new Ad(null, 1L, "PayPal", 35L, Date.valueOf(startingDate), Date.valueOf(dueDate), "1L");
        ad = new Ad(1L, 1L, "TodoMondo", 35L, Date.valueOf(startingDate), Date.valueOf(dueDate), "1L");
        persistAd = new Ad(1L, 1L, "TodoMondo", 35L, Date.valueOf(startingDate), Date.valueOf(dueDate), "1L");
    }

    @Test
    public void tc_1 () {
        when(repo.save(any(Ad.class))).thenReturn(persistAd);
        assertEquals(Long.valueOf(1L) ,dao.create(newAd).getId());
    }

    @Test
    public void tc_2 () {
        assertEquals(null, dao.create(persistAd));
    }

    @Test
    public void tc_3 () {

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(persistAd));
        when(repo.save(any(Ad.class))).thenReturn(ad);

        assertEquals(ad ,dao.update(ad));
    }

    @Test
    public void tc_4() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());
        assertEquals(null ,dao.update(ad));
    }

    @Test
    public void tc_5() {
        ArrayList<Ad> list = new ArrayList<Ad>();
        list.add(ad);

        when(repo.findAll()).thenReturn(list);
        Collection<Ad> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(ad));
    }

    @Test
    public void tc_6() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(ad));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(ad);
    }
}