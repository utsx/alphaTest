package com.alpha.utsx;

import com.alpha.utsx.clients.OpenExchangeClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestOpenExchange {

    @Autowired
    private OpenExchangeClient openExchangeClient;


    @Test
    public void TestConnection(){
    }

}
