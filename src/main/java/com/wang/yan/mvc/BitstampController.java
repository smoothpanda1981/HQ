package com.wang.yan.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.bitstamp.Ticker;

@Controller
@RequestMapping("/bitstamp")
public class BitstampController {
	private static final Logger logger = Logger.getLogger(BitstampController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String bitStampPage(ModelMap model) {
		model.addAttribute("message", "Bitstamp");
//		PusherOptions options = new PusherOptions();
//		options.setCluster("eu");
//		Pusher pusher = new Pusher("de504dc5763aeef9ff52", options);
//		pusher.connect();
//
//		Channel channel = pusher.subscribe("live_trades");
//		logger.info("YAN YAN");
//		channel.bind("trade", new SubscriptionEventListener() {
//			@Override
//			public void onEvent(String channelName, String eventName, String data) {
//				logger.info("test test test");
//				logger.info(data);
//			}
//		});


		try {
//			String result = getTickerBTCUSD("https://www.bitstamp.net/api/ticker/");
			String result = "{\"high\": \"2612.00\", \"last\": \"2540.08\", \"timestamp\": \"1499428242\", \"bid\": \"2540.23\", \"vwap\": \"2580.89\", \"volume\": \"7095.30631551\", \"low\": \"2540.00\", \"ask\": \"2543.98\", \"open\": 2599.01}";

			ObjectMapper mapper = new ObjectMapper();

			//JSON from file to Object
//			Staff obj = mapper.readValue(new File("c:\\file.json"), Staff.class);

			//JSON from URL to Object
//			Staff obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);

			//JSON from String to Object
			Ticker ticker = mapper.readValue(result, Ticker.class);

			logger.info("last " + ticker.getLast());
			logger.info("high " + ticker.getHigh());
			logger.info("open " + ticker.getOpen());

			model.addAttribute("result", ticker.getLast());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "bitstamp";
	}

	private String getTickerBTCUSD(String url) {


		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			logger.info("\nSending 'GET' request to URL : " + url);
			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}