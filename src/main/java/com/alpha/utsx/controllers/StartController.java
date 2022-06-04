package com.alpha.utsx.controllers;


import com.alpha.utsx.clients.GiphyService;
import com.alpha.utsx.clients.OpenExchangeClient;
import com.alpha.utsx.parsers.GsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class StartController {

    private OpenExchangeClient openExchangeClient;
    private GiphyService giphyService;
    private GsonParser parser;

    @Value("${giphy.api.token}")
    private String giphyToken;

    @Value("${open.exchange.token}")
    private String openExchangeToken;

    @Autowired
    public StartController(OpenExchangeClient openExchangeClient, GiphyService giphyService, GsonParser parser) {
        this.openExchangeClient = openExchangeClient;
        this.giphyService = giphyService;
        this.parser = parser;
    }

    @RequestMapping("/{exchange}")
    public ResponseEntity index(@PathVariable String exchange) throws ParseException {

        String result = computeExchange(exchange);
        if(result.equals("Invalid")){
            return ResponseEntity.ok("<center><h1>Enter valid exchange</h1><center>");
        }

        boolean buffer;
        buffer = Boolean.parseBoolean(result);

        return buffer ? ResponseEntity.ok("<center><img src=" + parser.parseGiphy(giphyService.up(giphyToken)) + "></center>") :
                ResponseEntity.ok("<center><img src=" + parser.parseGiphy(giphyService.down(giphyToken)) + "></center>");
    }

    private String computeExchange(String exchange){

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        double courseToday = 1.0;
        double courseYesterday = 1.0;

        try {
            courseToday = Double.parseDouble(parser.parseOpenExchange(openExchangeClient.historical(localDate.minusDays(1).format(formatter), openExchangeToken), exchange));
            courseYesterday = Double.parseDouble(parser.parseOpenExchange(openExchangeClient.historical(localDate.minusDays(2).format(formatter), openExchangeToken), exchange));
        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid";
        }

        return String.valueOf(courseToday > courseYesterday);
    }
}
