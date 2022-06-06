package com.alpha.utsx.controllers;


import com.alpha.utsx.Answer;
import com.alpha.utsx.clients.GiphyService;
import com.alpha.utsx.clients.OpenExchangeClient;
import com.alpha.utsx.parsers.GsonParser;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping(method = RequestMethod.GET, value = "/{exchange}")
public class StartController {


    private OpenExchangeClient openExchangeClient;

    private GiphyService giphyService;

    private GsonParser parser;

    @Value("${giphy.api.token}")
    private String giphyToken;

    @Value("${open.exchange.token}")
    private String openExchangeToken;

    private Answer answer;

    @Autowired
    public StartController(OpenExchangeClient openExchangeClient, GiphyService giphyService, GsonParser parser) {
        this.openExchangeClient = openExchangeClient;
        this.giphyService = giphyService;
        this.parser = parser;
    }

    @RequestMapping("")
    @ResponseBody
    public ResponseEntity index(@PathVariable String exchange) throws ParseException {
        answer = computeExchange(exchange);
        return ResponseEntity.ok("<center><img src=\"" + answer.getUrl() + "\"><br>" + answer + "</center>");
    }

    private Answer computeExchange(String exchange) throws ParseException {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        double courseToday = 1.0;
        double courseYesterday = 1.0;
        String openExchangeAnswer = "";
        try {

            openExchangeAnswer = this.openExchangeClient.historical(localDate.format(formatter), openExchangeToken);
            courseToday = Double.parseDouble(parser.parseOpenExchange(openExchangeAnswer, exchange));

            openExchangeAnswer =  this.openExchangeClient.historical(localDate.minusDays(1).format(formatter), openExchangeToken);
            courseYesterday = Double.parseDouble(parser.parseOpenExchange(openExchangeAnswer, exchange));

        } catch (Exception e) {
            return new Answer("USD", exchange, null, null, "invalid", "https://media.giphy.com/media/f6OakvYpFx3H0ShU3L/giphy.gif", "Invalid exchange");
        }
        return new Answer("USD", exchange, courseToday, courseYesterday, courseToday < courseYesterday ? "rich" : "broke",
                courseToday < courseYesterday ?  parser.parseGiphy(giphyService.up(giphyToken)) :  parser.parseGiphy(giphyService.down(giphyToken)), "OK");
    }


    public Answer getAnswer() {
        return answer;
    }
}
