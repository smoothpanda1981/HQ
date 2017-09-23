package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.BitstampProfit;
import com.wang.yan.mvc.model.bitstamp.Balance;
import com.wang.yan.mvc.model.bitstamp.Ticker;
import com.wang.yan.mvc.model.bitstamp.UserTransaction;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/money")
public class MoneyController {
	private static final Logger logger = Logger.getLogger(MoneyController.class);

	@Autowired
	private BitstampService bitstampService;


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
				if (s.contains("key")) {
					key = s.substring(4, s.length());
				} else if (s.contains("password")) {
					password = s.substring(9, s.length());
				} else {
					account = s.substring(8, s.length());
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
			BigDecimal balance_btceur_balance = new BigDecimal(balance.getBtc_balance());
			balance_btceur_balance =  balance_btceur_balance.multiply(new BigDecimal(ticker_btceur.getLast()));
			logger.info("balance_btceur_balance : " + balance_btceur_balance.toString());

			profit = profit.add(balance_btcusd_balance);
			profit = profit.add(balance_btceur_balance);
			profit = new BigDecimal(balance.getUsd_available()).subtract(profit);

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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}



		return "money";
	}


}
