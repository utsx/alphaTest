package com.alpha.utsx.clients;


import com.fasterxml.jackson.databind.util.JSONPObject;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "openExchange", url = "https://openexchangerates.org/api/")
public interface OpenExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "historical/{data}.json?app_id={openExchangeToken}")
    String historical(@PathVariable("data") String data, @PathVariable("openExchangeToken") String openExchangeToken);

    @RequestMapping(method = RequestMethod.GET, value = "latest.json?app_id={openExchangeToken}")
    String latest(@PathVariable("openExchangeToken") String openExchangeToken);

}
