package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.trawell.models.Photo;
import com.trawell.repositories.PhotoRepository;
import com.trawell.services.PhotoService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
    public class Test_PhotoService{

    @InjectMocks
    PhotoService dao;

    @Mock
    PhotoRepository repo;

    Photo newphoto;
    Photo photo;

    @Before public void init() {
        newphoto= new Photo();
        photo= new Photo();
        photo.setId(1L);
        photo.setPath(".../img.png");
        
        newphoto.setPath((".../img.png"));
    }

    @Test
    public void TC_ph(){

        when(repo.save(any(Photo.class))).thenReturn(photo);

        assertEquals(Long.valueOf(1L) ,dao.create(newphoto).getId());
    }

    @Test
    public void TC_ph2(){

        assertEquals(null, dao.create(photo));
    }

    @Test
    public void TC_ph3(){
        Photo savephoto = new Photo();
        savephoto.setId(1L);
        savephoto.setPath(".../imgs.png");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(savephoto));
        when(repo.save(any(Photo.class))).thenReturn(photo);

        assertEquals(photo ,dao.update(photo));
    }
   
    @Test
    public void TC_ph4(){
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(photo));
    }

    @Test
    public void TC_ph5(){
        ArrayList<Photo> list = new ArrayList<>();
        list.add(photo);

        when(repo.findAll()).thenReturn(list);
        Collection<Photo> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(photo));
    }

    @Test
    public void TC_ph6(){
        when(repo.findById(1L)).thenReturn(Optional.of(photo));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(photo);
    }
}