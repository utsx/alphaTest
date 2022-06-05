package com.alpha.utsx.parsers;

import com.alpha.utsx.clients.GiphyService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GsonParserTest {


    @Autowired
    private GsonParser gsonParser;

    @Test
    void parseGiphy() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/test/java/com/alpha/utsx/parsers/samples/Giphy.json"));
        String expected = gsonParser.parseGiphy(jsonObject.toJSONString());
        assertEquals(expected, "https://media4.giphy.com/media/wosPSFuDHaqSXqr8lD/giphy.gif?cid=2c8168c452272f85466467169ed36dd61f20573c8c0dfb03&rid=giphy.gif&ct=g");
    }

    @Test
    void parseOpenExchange() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/test/java/com/alpha/utsx/parsers/samples/OpenExchange.json"));
        String expected = gsonParser.parseOpenExchange(jsonObject.toJSONString(), "RUB");
        assertEquals(expected, "63.360006");
    }
}