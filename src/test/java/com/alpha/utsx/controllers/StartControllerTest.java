package com.alpha.utsx.controllers;


import com.alpha.utsx.Answer;
import com.alpha.utsx.clients.GiphyService;
import com.alpha.utsx.clients.OpenExchangeClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.geom.RectangularShape;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StartControllerTest {


    @Autowired
    @MockBean
    private OpenExchangeClient openExchangeClient;

    @Autowired
    @MockBean
    private GiphyService giphyService;

    @Autowired
    private StartController startController;

    @Value("${open.exchange.token}")
    private String openExchangeToken;

    @Test
    public void openExchangeValidExchangeTest() throws Exception {
        JSONParser parser = new JSONParser();
        ResponseEntity responseEntity = startController.index("RUB");
        System.out.println(responseEntity);
        Answer answer = startController.getAnswer();
        Assert.assertEquals(answer.getType(), "invalid");
    }

    @Test
    public void GiphyTestValid() throws ParseException {
        JSONParser parser = new JSONParser();
        ResponseEntity responseEntity = startController.index("RUR");
        Answer answer = startController.getAnswer();
        Assert.assertEquals(answer.getUrl(), null);
    }

    @Test
    public void GiphyTestInvalid() throws ParseException {
        JSONParser parser = new JSONParser();
        ResponseEntity responseEntity = startController.index("RUB");
        Answer answer = startController.getAnswer();
        Assert.assertNull(answer.getUrl());
    }

    @Test
    public void openExchangeInvalidExchangeTest() throws Exception {
        JSONParser parser = new JSONParser();
        ResponseEntity responseEntity = startController.index("RUR");
        Answer answer = startController.getAnswer();
        Assert.assertEquals(answer.getType(), "invalid");
    }
}