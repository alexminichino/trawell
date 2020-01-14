package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.trawell.models.BanData;
import com.trawell.repositories.BanDataRepository;
import com.trawell.services.BanDataService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_BanDataService {
    @InjectMocks
    BanDataService dao;
    
    @Mock
    BanDataRepository repo;

    BanData newBData;
    BanData bData;
    BanData upBData;

    @Before public void init () {
        String dateNewBData = String.format("%d-%02d-%02d", 2020, 2, 1);
        String dateBData = String.format("%d-%02d-%02d", 2020, 2, 1);
        String dateUpBData = String.format("%d-%02d-%02d", 2020, 3, 1);
        newBData = new BanData(null, 1L, 1L, Date.valueOf(dateNewBData),"no specific reason");
        bData = new BanData(1L, 1L, 1L, Date.valueOf(dateBData),"no specific reason");
        upBData = new BanData(1L, 1L, 1L, Date.valueOf(dateUpBData),"no specific reason"); 
      }

    @Test
    public void tc_1 () {
        when(repo.save(any(BanData.class))).thenReturn(bData);
        assertEquals(Long.valueOf(1L) ,dao.create(newBData).getId());
    }

    @Test
    public void tc_2 () {
        assertEquals(null, dao.create(bData));
    }

    @Test
    public void tc_3() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(bData));
        when(repo.save(any(BanData.class))).thenReturn(upBData);
        assertEquals(upBData, dao.update(upBData));
    }

    @Test
    public void tc_4 () {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());
        assertEquals(null ,dao.update(upBData));
    }

    @Test
    public void tc_5() {
        ArrayList<BanData> list = new ArrayList<BanData>();
        list.add(bData);

        when(repo.findAll()).thenReturn(list);
        Collection<BanData> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(bData));
    }

    @Test
    public void tc_6() {
        when(repo.findById(1L)).thenReturn(Optional.of(bData));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(bData);
    }
}