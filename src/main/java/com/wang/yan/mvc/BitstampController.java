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
		String url = "https://www.bitstamp.net/api/ticker/";

		URL obj = null;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "bitstamp";
	}
}