package com.alpha.utsx.clients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "GIPHY", url = "https://api.giphy.com/v1/gifs/random")
public interface GiphyService {

    @RequestMapping(method = RequestMethod.GET, value = "?tag=rich&limit=1&api_key={giphy}")
    public String up(@PathVariable String giphy);

    @RequestMapping(method = RequestMethod.GET, value = "?tag=broke&limit=1&api_key={giphy}")
    public String down(@PathVariable String giphy);


}
