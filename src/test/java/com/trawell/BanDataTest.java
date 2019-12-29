package com.trawell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import com.trawell.models.BanData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Mario Paone 
 */
public class BanDataTest {

    private BanData bandata;
    
    @BeforeEach
    public void setUp() throws Exception {
        long a = 9;
        long b = 679;
        long c = 69;
        Date data = Date.valueOf("2021-03-12");
        bandata = new BanData(a,c,b, data, "linguaggio inappropriato");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("Test Completed");

    }

    @Test
    public void testBanDataConstr() throws Exception {
        assertEquals(9, bandata.getId());
        assertEquals(69, bandata.getIdUser());
        assertEquals(679,bandata.getIdAdmin());
        assertEquals("linguaggio inappropriato", bandata.getMotivation());
        assertEquals(Date.valueOf("2021-03-12"),bandata.getBanUntil());

    }



    @Test
    public void testBanDataSetter() throws Exception {
        long a = 10;
        long b = 730;
        long c = 54;
        bandata.setId(a);
        bandata.setIdUser(c);
        bandata.setIdAdmin(b);
        bandata.setMotivation("uso di termini scurrili");
        bandata.setBanUntil(Date.valueOf("2022-12-12"));

        assertEquals(10, bandata.getId());
        assertEquals(54, bandata.getIdUser());
        assertEquals(730,bandata.getIdAdmin());
        assertEquals("uso di termini scurrili", bandata.getMotivation());
        assertEquals(Date.valueOf("2022-12-12"),bandata.getBanUntil());

    }


}