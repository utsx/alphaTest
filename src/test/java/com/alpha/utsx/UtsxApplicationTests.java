package com.alpha.utsx;

import com.alpha.utsx.clients.GiphyService;
import com.alpha.utsx.clients.OpenExchangeClient;
import net.minidev.json.JSONUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.SecureRandom;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class UtsxApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Value("${giphy.api.token}")
	private String giphyToken;

	@Value("${open.exchange.token}")
	private String openExchangeToken;

	private Answer answer;

	@Autowired
	private OpenExchangeClient openExchangeClient;

	@Autowired
	private GiphyService giphyService;

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/RUB")).andExpect(status().isOk());
	}

	@Test
	void openExchangeStatus() throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(openExchangeClient.latest(openExchangeToken));
		Assertions.assertNotEquals(jsonObject.get("rates"), null);
	}

	@Test
	void giphyStatus() throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(giphyService.up(giphyToken));
		jsonObject = (JSONObject) jsonObject.get("meta");
		int actual = (Integer.parseInt(String.valueOf(jsonObject.get("status"))));
		Assertions.assertEquals(actual, 200);
	}

}
