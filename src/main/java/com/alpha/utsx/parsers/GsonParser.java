package com.alpha.utsx.parsers;

import lombok.SneakyThrows;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@Component
public class GsonParser {

    public GsonParser(){
    }


    public String parseGiphy(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        jsonObject = (JSONObject) jsonObject.get("data");
        jsonObject = (JSONObject) jsonObject.get("images");
        jsonObject = (JSONObject) jsonObject.get("original");
        return (String) jsonObject.get("url");
    }

    public String parseOpenExchange(String json, String exchange) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        jsonObject = (JSONObject) jsonObject.get("rates");
        String ans = "";
        try{
            ans = String.valueOf(jsonObject.get(exchange));
        }
        catch (Exception e){
            return "Invalid";
        }
        return ans;
    }
}

