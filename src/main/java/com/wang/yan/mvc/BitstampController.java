package com.wang.yan.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

@Controller
@RequestMapping("/bitstamp")
public class BitstampController {
	private static final Logger logger = Logger.getLogger(BitstampController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String bitStampPage(ModelMap model) {
		model.addAttribute("message", "Bitstamp");
		PusherOptions options = new PusherOptions();
		options.setCluster("eu");
		Pusher pusher = new Pusher("de504dc5763aeef9ff52", options);
		pusher.connect();

		Channel channel = pusher.subscribe("live_trades");
		logger.info("YAN YAN");
		channel.bind("trade", new SubscriptionEventListener() {
			@Override
			public void onEvent(String channelName, String eventName, String data) {
				logger.info("test test test");
				logger.info(data);
			}
		});
		return "bitstamp";
	}
}