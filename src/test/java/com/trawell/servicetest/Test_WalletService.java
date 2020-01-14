package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.trawell.models.Photo;
import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.repositories.JPAGroupRepository;
import com.trawell.repositories.PhotoRepository;
import com.trawell.repositories.WalletRepository;
import com.trawell.services.PhotoService;
import com.trawell.services.WalletService;

import java.sql.Date;
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
public class Test_WalletService{
    @InjectMocks
    WalletService dao;

    @Mock
    JPAGroupRepository repoGroup;
    @Mock
    WalletRepository repoWallet;

    Wallet wallet;
    Wallet newallet;

    @Before public void init(){
        TrawellGroup gru = new TrawellGroup();
        User user = new User();
        wallet= new Wallet();
        newallet= new Wallet();
         gru.setId(1L);
         user.setId(1L);
         wallet.setId(1L);
         wallet.setPrivate(true);
         wallet.setUser(user);
         wallet.setGroup(gru);

         newallet.setGroup(gru);
         newallet.setPrivate(true);
         newallet.setUser(user);
    }

    @Test
    public void TC_wall(){

        when(repoWallet.save(any(Wallet.class))).thenReturn(wallet);

        assertEquals(Long.valueOf(1L) ,dao.create(newallet).getId());
    }

    @Test
    public void TC_wall2(){

        assertEquals(null, dao.create(wallet));
    }

    @Test
    public void TC_wall3(){
        User user= new User();
        TrawellGroup gr = new TrawellGroup();
        Wallet saveWallet= new Wallet();
         saveWallet.setId(1L);
         saveWallet.setPrivate(true);
         saveWallet.setUser(user);
         gr.setId(2L);
         saveWallet.setGroup(gr);

         when(repoWallet.findById(Long.valueOf(1L))).thenReturn(Optional.of(saveWallet));
         when(repoWallet.save(any(Wallet.class))).thenReturn(wallet);
 
         assertEquals(wallet ,dao.update(wallet));

    }

    @Test
    public void TC_wall4(){

        when(repoWallet.findById(Long.valueOf(1L))).thenReturn(Optional.empty());
        assertEquals(null ,dao.update(wallet));
    }
  

    @Test
    public void TC_wall5(){
        ArrayList<Wallet> list = new ArrayList<>();
        list.add(wallet);

        when(repoWallet.findAll()).thenReturn(list);
        Collection<Wallet> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(wallet));
    }

    @Test
    public void TC_wall6(){
        when(repoWallet.findById(1L)).thenReturn(Optional.of(wallet));
        dao.delete(1L);
        Mockito.verify(repoWallet, times(1)).delete(wallet);
    }
}