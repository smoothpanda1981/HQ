package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.wang.yan.mvc.model.BitstampProfit;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.bitstamp.Balance;
import com.wang.yan.mvc.model.bitstamp.Ticker;
import com.wang.yan.mvc.model.bitstamp.UserTransaction;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.service.FedexService;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/money")
public class MoneyController {
	private static final Logger logger = Logger.getLogger(MoneyController.class);

	@Autowired
	private BitstampService bitstampService;

	@Autowired
	private FedexService fedexService;

	@RequestMapping(method = RequestMethod.GET)
	public String moneyPage(ModelMap model) throws InterruptedException {
		model.addAttribute("message", "Money");

		BitstampUtils bitstampUtils = new BitstampUtils();
		try {
			ObjectMapper mapper = new ObjectMapper();
			/*
				authentication
		 	*/
			String key = "";
			String password = "";
			String account = "";
			List<String> stringList = bitstampUtils.getAuthKeys();
			for (String s : stringList) {
				if (s.startsWith("key=")) {
					key = s.substring(4, s.length());
				} else if (s.startsWith("password=")) {
					password = s.substring(9, s.length());
				} else if (s.startsWith("account=")) {
					account = s.substring(8, s.length());
				} else {
					// nothing
				}
			}
			logger.info("bitstamp key : " + key);
			logger.info("bitstamp password : " + password);
			logger.info("bitstamp account : " + account);
			bitstampUtils.setAuthKeys(key, password, account);

			/*
				ticker BTC USD
			 */
			StringBuffer response = bitstampUtils.getGetData("https://www.bitstamp.net/api/v2/ticker/btcusd/", "ticker_btcusd");
			logger.info("ticker btc_usd : " + response.toString());
			Ticker ticker_btcusd = mapper.readValue(response.toString(), Ticker.class);
			model.addAttribute("ticker_btcusd_last", ticker_btcusd.getLast());
			response = bitstampUtils.getGetData("https://www.bitstamp.net/api/v2/ticker/btceur/", "ticker_btceur");
			logger.info("ticker btc_eur : " + response.toString());
			Ticker ticker_btceur = mapper.readValue(response.toString(), Ticker.class);
			model.addAttribute("ticker_btceur_last", ticker_btceur.getLast());

			/*
				balance
		 	*/
			response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/balance/", "balance");
			logger.info("balance : " + response.toString());
			Balance balance = mapper.readValue(response.toString(), Balance.class);
			model.addAttribute("btc_balance", balance.getBtc_balance());
			model.addAttribute("eth_balance", balance.getEth_balance());
			model.addAttribute("usd_available", balance.getUsd_available());
			model.addAttribute("eur_available", balance.getEur_available());

			/*
				user_transaction
		 	*/
			response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/user_transactions/", "user_transaction");
			logger.info("user_transactions : " + response.toString());
			List<UserTransaction> userTransactionList = mapper.readValue(response.toString(), new TypeReference<List<UserTransaction>>(){});
			logger.info("test 1: " + userTransactionList.get(0).getBtc());

			BigDecimal depositAmount_usd = new BigDecimal(0);
			BigDecimal depositAmount_eur = new BigDecimal(0);

			BigDecimal buyAmount = new BigDecimal(0);

			BigDecimal sellAmount = new BigDecimal(0);

			BigDecimal withDrawAmount_usd = new BigDecimal(0);
			BigDecimal withDrawAmount_eur = new BigDecimal(0);

			BigDecimal profit = new BigDecimal(0);

			for (UserTransaction userTransaction : userTransactionList) {
				if (userTransaction.getType().equals("0")) {
					BigDecimal userTransUsd = new BigDecimal(userTransaction.getUsd());
					BigDecimal userTransEur = new BigDecimal(userTransaction.getEur());

					if (userTransUsd.compareTo(BigDecimal.ZERO) > 0) {
						logger.info("deposit usd : " + userTransaction.getUsd());
						depositAmount_usd = depositAmount_usd.add(userTransUsd);
					} else {
						logger.info("deposit eur : " + userTransaction.getEur());
						depositAmount_eur = depositAmount_eur.add(userTransEur);
					}
				}

				if (userTransaction.getType().equals("2")) {
					BigDecimal amountBtcValue = new BigDecimal(userTransaction.getUsd());
					if (userTransaction.getBtc() >= 0.0) {
						//logger.info("buy : " + userTransaction.getUsd());
						buyAmount = buyAmount.add(amountBtcValue);
					} else {
						//logger.info("sell : " + userTransaction.getUsd());
						sellAmount = sellAmount.add(amountBtcValue);
					}
				}

				if (userTransaction.getType().equals("1")) {
					BigDecimal userTransUsd = new BigDecimal(userTransaction.getUsd());
					BigDecimal userTransEur = new BigDecimal(userTransaction.getEur());

					if (userTransUsd.compareTo(BigDecimal.ZERO) < 0) {
						logger.info("withdraw usd : " + userTransaction.getUsd());
						withDrawAmount_usd = withDrawAmount_usd.add(userTransUsd.subtract(new BigDecimal(userTransaction.getFee())));
					} else {
						logger.info("withdraw eur : " + userTransaction.getEur());
						withDrawAmount_eur = withDrawAmount_eur.add(userTransEur.subtract(new BigDecimal(userTransaction.getFee())));
					}
				}
			}
			depositAmount_usd = depositAmount_usd.setScale(2, RoundingMode.CEILING);
			depositAmount_eur = depositAmount_eur.setScale(2, RoundingMode.CEILING);
			buyAmount = buyAmount.setScale(2, RoundingMode.CEILING);
			sellAmount = sellAmount.setScale(2, RoundingMode.CEILING);
			withDrawAmount_usd = withDrawAmount_usd.setScale(2, RoundingMode.CEILING);
			withDrawAmount_eur = withDrawAmount_eur.setScale(2, RoundingMode.CEILING);


			// Compute profit
			profit = depositAmount_usd.add(withDrawAmount_usd);
			profit = profit.add(depositAmount_eur.add(withDrawAmount_eur));
			BigDecimal balance_btcusd_balance = new BigDecimal(balance.getBtc_balance());
			balance_btcusd_balance =  balance_btcusd_balance.multiply(new BigDecimal(ticker_btcusd.getLast()));
			logger.info("balance_btcusd_balance : " + balance_btcusd_balance.toString());
//			BigDecimal balance_btceur_balance = new BigDecimal(balance.getBtc_balance());
//			balance_btceur_balance =  balance_btceur_balance.multiply(new BigDecimal(ticker_btceur.getLast()));
//			logger.info("balance_btceur_balance : " + balance_btceur_balance.toString());

			profit = profit.add(balance_btcusd_balance);
			profit = balance_btcusd_balance.add(new BigDecimal(balance.getUsd_available())).subtract(profit);

			profit = profit.setScale(2, RoundingMode.CEILING);


			model.addAttribute("depositAmountUsd", depositAmount_usd.toString());
			model.addAttribute("depositAmountEur", depositAmount_eur.toString());
			model.addAttribute("buyAmount", buyAmount.toString());
			model.addAttribute("sellAmount", sellAmount.toString());
			model.addAttribute("withDrawAmountUsd", withDrawAmount_usd.toString());
			model.addAttribute("withDrawAmountEur", withDrawAmount_eur.toString());
			model.addAttribute("profitAmount", Double.valueOf(profit.toString()));

			/*
				Check if needed to save in BitstampProfit
			 */
			BitstampProfit bitstampProfitToSave = new BitstampProfit();
			bitstampProfitToSave.setProfit(Double.valueOf(profit.toString()));
			bitstampProfitToSave.setCreationDate(new Date());

			BitstampProfit bitstampProfit = bitstampService.getLastBitstampProfit();
			if (bitstampProfit.getProfit() == null) {
				bitstampService.saveBitstampProfit(bitstampProfitToSave);
			} else {
				if (!bitstampProfit.getProfit().equals(bitstampProfitToSave.getProfit())) {
					bitstampService.saveBitstampProfit(bitstampProfitToSave);
				}
			}
			BitstampProfit bitstampProfitForAttribute = bitstampService.getLastBitstampProfit();
			model.addAttribute("bitstampProfit", bitstampProfitForAttribute);

			/*
				Fedex profit
			 */
			try {
				List<Fedex> fedexList = fedexService.getListFedex();
				Double total = 0.00;
				for (Fedex fedex : fedexList) {
					total = total + fedex.getPaid();
				}
				total = total * 0.04 * 0.49;
				BigDecimal fedexProfit = new BigDecimal(total.doubleValue());
				fedexProfit = fedexProfit.setScale(2, RoundingMode.CEILING);

				model.addAttribute("fedexProfit", fedexProfit.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			/*
				Fedex profit
			 */
			// Build a new authorized API client service.
//			Sheets service = getSheetsService();
//
//			String spreadsheetId = "1upPMjqyXS3B-YZN1McDamm39XHLaSwkq58bWs4OsxNQ";
//			String range = "822212646!E:E";
//			ValueRange responseValueRande = service.spreadsheets().values()
//					.get(spreadsheetId, range)
//					.execute();
//			List<List<Object>> values = responseValueRande.getValues();
//			if (values == null || values.size() == 0) {
//				System.out.println("No data found.");
//			} else {
//				System.out.println("Name, Major");
//				for (List row : values) {
//					System.out.printf("%s, %s\n", row.get(0), row.get(4));
//				}
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		return "money";
	}


	/**
	 * Build and return an authorized Sheets API client service.
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = authorize();
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName("Google Sheets API Java - YW")
				.build();
	}

	public static Credential authorize() throws IOException, GeneralSecurityException {
		// Load client secrets.
		Resource resource = new ClassPathResource("google/client_id.json");
		InputStream in = resource.getInputStream();

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,  Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY))
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(
								System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart")))
						.setAccessType("offline")
						.build();
		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		System.out.println(
				"Credentials saved to " + new java.io.File(
						System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart").getAbsolutePath());
		return credential;
	}
}
