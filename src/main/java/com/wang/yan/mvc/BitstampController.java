package com.wang.yan.mvc;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wang.yan.mvc.model.bitstamp.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Controller
@RequestMapping("/bitstamp")
public class BitstampController {
	private static final Logger logger = Logger.getLogger(BitstampController.class);

	private SecretKeySpec keyspec;
	private Mac mac;
	private String key;
	private String clientid;
	private long nonce;

	@RequestMapping(method = RequestMethod.GET)
	public String bitStampPage(ModelMap model) {
		model.addAttribute("message", "Bitstamp");

		/*
		Pusher
		 */
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
			/*
				get current price
		 	*/
//			String result = getTickerBTCUSD("https://www.bitstamp.net/api/ticker/");
//			String result = "{\"high\": \"2612.00\", \"last\": \"2540.08\", \"timestamp\": \"1499428242\", \"bid\": \"2540.23\", \"vwap\": \"2580.89\", \"volume\": \"7095.30631551\", \"low\": \"2540.00\", \"ask\": \"2543.98\", \"open\": 2599.01}";

			ObjectMapper mapper = new ObjectMapper();

			//JSON from file to Object
//			Ticker ticker = mapper.readValue(new File("c:\\file.json"), Ticker.class);

			//JSON from URL to Object
			Ticker ticker = mapper.readValue(new URL("https://www.bitstamp.net/api/ticker/"), Ticker.class);

			//JSON from String to Object
//			Ticker ticker = mapper.readValue(result, Ticker.class);

			logger.info("last " + ticker.getLast());
			logger.info("high " + ticker.getHigh());
			logger.info("open " + ticker.getOpen());

			model.addAttribute("result", ticker.getLast());

/*
				order_book (pubilc call)
		 	*/
			OrderBook orderBook = mapper.readValue(new URL("https://www.bitstamp.net/api/order_book/"), OrderBook.class);
			logger.info("timestamp : " + orderBook.getTimestamp());
			logger.info("bids : " + orderBook.getBids().get(0));
			logger.info("asks : " + orderBook.getAsks().get(0));

			List<Bids> bidsList = new ArrayList<Bids>();
			int counter = 0;
			for (int i = 0; i < orderBook.getBids().size() && counter < 50; i++) {
				String tab = orderBook.getBids().get(i).toString();
				String result = tab.replace("[", "");
				result = result.replace("]", "");
				result = result.trim();
				String[] sTab = result.split(",");
				logger.info("price : " + sTab[0] + " quantity : " + sTab[1]);
				Bids bids = new Bids();
				bids.setPrice(Double.valueOf(sTab[0]));
				bids.setBtcQuantity(Double.valueOf(sTab[1]));
				bidsList.add(bids);
				counter++;
			}


			List<Asks> asksList = new ArrayList<Asks>();
			counter = 0;
			for (int i = 0; i < orderBook.getAsks().size() && counter < 50; i++) {
				String tab = orderBook.getAsks().get(i).toString();
				String result = tab.replace("[", "");
				result = result.replace("]", "");
				result = result.trim();
				String[] sTab = result.split(",");
				logger.info("price : " + sTab[0] + " quantity : " + sTab[1]);
				Asks asks = new Asks();
				asks.setPrice(Double.valueOf(sTab[0]));
				asks.setBtcQuantity(Double.valueOf(sTab[1]));
				asksList.add(asks);
				counter++;
			}
			model.addAttribute("bidsList", bidsList);
			model.addAttribute("asksList", asksList);

			/*
				authentication
		 	*/
			setAuthKeys("njOkn5ghkE2GFui01Wh94eyy7FCBekpk", "B1iF44lKdMalQXCy2viXg4FkPKLD1bUG", "670702");

			/*
				balance
		 	*/
			StringBuffer response = getPostData("https://www.bitstamp.net/api/v2/balance/", "balance");
			logger.info(response.toString());
			Balance balance = mapper.readValue(response.toString(), Balance.class);
			model.addAttribute("btc_balance", balance.getBtc_balance());
			model.addAttribute("usd_available", balance.getUsd_available());

			/*
				user_transaction
		 	*/
			response = getPostData("https://www.bitstamp.net/api/v2/user_transactions/", "user_transaction");
			logger.info(response.toString());
			List<UserTransaction> userTransactionList = mapper.readValue(response.toString(), new TypeReference<List<UserTransaction>>(){});
			logger.info("test 1: " + userTransactionList.get(0).getBtc());

			List<UserTransaction> boughtUserTransationList = new ArrayList<UserTransaction>();
			List<UserTransaction> soldUserTransationList = new ArrayList<UserTransaction>();
			List<UserTransaction> depositUserTransationList = new ArrayList<UserTransaction>();

			for (UserTransaction userTransaction : userTransactionList) {
				if (userTransaction.getType().equals("0")) {
					depositUserTransationList.add(userTransaction);
				} else {
					if (userTransaction.getBtc() > 0.0) {
						boughtUserTransationList.add(userTransaction);
					} else {
						soldUserTransationList.add(userTransaction);
					}
				}
			}

			List<ProfitsBuySell> profitsBuySellsList = new ArrayList<ProfitsBuySell>();

			List<Integer> sellIdsListToIgnore = new ArrayList<Integer>();


			for (UserTransaction buy_transaction : boughtUserTransationList) {
				BigDecimal profitAndLoss = new BigDecimal(0);
				profitAndLoss = profitAndLoss.setScale(2, BigDecimal.ROUND_CEILING);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date buydate = sdf.parse(buy_transaction.getDatetime());

				ProfitsBuySell profitsBuySell = new ProfitsBuySell();

				profitsBuySell.setBtc_buy(buy_transaction.getBtc());
				profitsBuySell.setBtc_usd_buy(buy_transaction.getBtc_usd());
				profitsBuySell.setDatetime_buy(buy_transaction.getDatetime());
				profitsBuySell.setEur_buy(buy_transaction.getEur());
				profitsBuySell.setFee_buy(buy_transaction.getFee());
				profitsBuySell.setId_buy(buy_transaction.getId());
				profitsBuySell.setOrder_id_buy(buy_transaction.getOrder_id());
				profitsBuySell.setType_buy(buy_transaction.getType());
				profitsBuySell.setUsd_buy(buy_transaction.getUsd());
//				BigDecimal t = new BigDecimal(buy_transaction.getUsd());
//				t = t.setScale(2, BigDecimal.ROUND_CEILING);
//				profitAndLoss = profitAndLoss.add(t);
//				BigDecimal s = new BigDecimal(buy_transaction.getFee());
//				s = s.setScale(2, BigDecimal.ROUND_CEILING);
//				profitAndLoss = profitAndLoss.subtract(s);

				for (UserTransaction sell_transaction : soldUserTransationList) {
					Date selldate = sdf.parse(sell_transaction.getDatetime());
					if (Math.abs(sell_transaction.getBtc()) == buy_transaction.getBtc() && selldate.compareTo(buydate) > 0) {
						profitsBuySell.setBtc_sell(sell_transaction.getBtc());
						profitsBuySell.setBtc_usd_sell(sell_transaction.getBtc_usd());
						profitsBuySell.setDatetime_sell(sell_transaction.getDatetime());
						profitsBuySell.setEur_sell(sell_transaction.getEur());
						profitsBuySell.setFee_sell(sell_transaction.getFee());
						profitsBuySell.setId_sell(sell_transaction.getId());
						profitsBuySell.setOrder_id_sell(sell_transaction.getOrder_id());
						profitsBuySell.setType_sell(sell_transaction.getType());
						profitsBuySell.setUsd_sell(sell_transaction.getUsd());

						BigDecimal a = new BigDecimal(sell_transaction.getUsd());
						BigDecimal b = new BigDecimal(buy_transaction.getUsd());

						BigDecimal c = new BigDecimal(sell_transaction.getFee());
						BigDecimal d = new BigDecimal(buy_transaction.getFee());


						a = a.setScale(2, BigDecimal.ROUND_CEILING);
						b = b.setScale(2, BigDecimal.ROUND_CEILING);
						c = c.setScale(2, BigDecimal.ROUND_CEILING);
						d = d.setScale(2, BigDecimal.ROUND_CEILING);


						logger.info("a = " + a);
						logger.info("b = " + b);
						logger.info("c = " + c);
						logger.info("d = " + d);
						profitAndLoss = new BigDecimal(0);
						profitAndLoss = profitAndLoss.setScale(2, BigDecimal.ROUND_CEILING);
						profitAndLoss = profitAndLoss.add(a);
						profitAndLoss = profitAndLoss.add(b);
						profitAndLoss = profitAndLoss.subtract(c);
						profitAndLoss = profitAndLoss.subtract(d);

						logger.info("profitAndLoss = " + profitAndLoss);
						sellIdsListToIgnore.add(sell_transaction.getId());
					}
				}

				profitsBuySell.setProfitAndLose(profitAndLoss);

				profitsBuySellsList.add(profitsBuySell);
			}

			for (UserTransaction sell_transaction : soldUserTransationList) {
				boolean testIfIgnore = false;
				for (Integer i : sellIdsListToIgnore) {
					if (i == sell_transaction.getId()) {
						testIfIgnore = true;
					}
				}

				if (!testIfIgnore) {
					ProfitsBuySell profitsBuySell = new ProfitsBuySell();
					profitsBuySell.setBtc_sell(sell_transaction.getBtc());
					profitsBuySell.setBtc_usd_sell(sell_transaction.getBtc_usd());
					profitsBuySell.setDatetime_sell(sell_transaction.getDatetime());
					profitsBuySell.setEur_sell(sell_transaction.getEur());
					profitsBuySell.setFee_sell(sell_transaction.getFee());
					profitsBuySell.setId_sell(sell_transaction.getId());
					profitsBuySell.setOrder_id_sell(sell_transaction.getOrder_id());
					profitsBuySell.setType_sell(sell_transaction.getType());
					profitsBuySell.setUsd_sell(sell_transaction.getUsd());

//					BigDecimal t = new BigDecimal(sell_transaction.getUsd());
//					t = t.setScale(2, BigDecimal.ROUND_CEILING);
					BigDecimal profitAndLoss = new BigDecimal(0);
					profitAndLoss = profitAndLoss.setScale(2, BigDecimal.ROUND_CEILING);
//					profitAndLoss = profitAndLoss.add(t);
//					BigDecimal s = new BigDecimal(sell_transaction.getFee());
//					s = s.setScale(2, BigDecimal.ROUND_CEILING);
//					profitAndLoss = profitAndLoss.subtract(s);
					profitsBuySell.setProfitAndLose(profitAndLoss);

					profitsBuySellsList.add(profitsBuySell);
				}

			}
			model.addAttribute("profitsBuySellsList", profitsBuySellsList);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "bitstamp";
	}

	private StringBuffer getPostData(String httpLink, String requestName) throws ParseException, IOException {
		Map<String,String> args = new HashMap<String,String>() ;

//		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//Date d = f.parse(f.format(new Date()));
		//long milliseconds = d.getTime();
		this.nonce = System.currentTimeMillis();

		args.put("nonce", Long.toString(this.nonce)) ;
		args.put("key", this.key) ;

		if (requestName.equals("user_transaction")) {
			args.put("limit", Integer.toString(1000));
		}
		// create url form encoded post data
		String postData = "" ;
		for (Iterator<String> iter = args.keySet().iterator(); iter.hasNext();) {
			String arg = iter.next() ;
			if (postData.length() > 0) postData += "&" ;
			postData += arg + "=" + URLEncoder.encode(args.get(arg)) ;
		}


		//URL url = new URL("https://www.bitstamp.net/api/v2/balance/");
		URL url = new URL(httpLink);
		URLConnection conn = url.openConnection() ;
		conn.setUseCaches(false) ;
		conn.setDoOutput(true) ;

		mac.update(Long.toString(this.nonce).getBytes()) ;
		mac.update(this.clientid.getBytes()) ;
		mac.update(this.key.getBytes()) ;

		postData += "&signature="+String.format("%064x", new BigInteger(1, mac.doFinal())).toUpperCase() ;
		logger.info("postData : " + postData);
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded") ;
		conn.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36") ;

		// write post data
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(postData) ;
		out.close() ;

		// read response
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		StringBuffer response = new StringBuffer() ;
		while ((line = in.readLine()) != null)
			response.append(line) ;
		in.close() ;


		return response;
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


	private void setAuthKeys(String key,String secret,String clientid) {
		try {
			this.keyspec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256") ;
			this.mac = Mac.getInstance("HmacSHA256") ;
			this.key = key;
			this.clientid = clientid;
			mac.init(keyspec) ;
			logger.info(mac.getMacLength());
			logger.info(mac.getAlgorithm());
			logger.info(mac.getProvider());
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}


	}
}
