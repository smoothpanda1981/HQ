package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.bitstamp.*;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/money")
public class MoneyController {
	private static final Logger logger = Logger.getLogger(MoneyController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String moneyPage(ModelMap model) throws InterruptedException {
		model.addAttribute("message", "Money");

		BitstampUtils bitstampUtils = new BitstampUtils();
		try {
			ObjectMapper mapper = new ObjectMapper();
			/*
				authentication
		 	*/
			bitstampUtils.setAuthKeys("njOkn5ghkE2GFui01Wh94eyy7FCBekpk", "B1iF44lKdMalQXCy2viXg4FkPKLD1bUG", "670702");

			/*
				ticket BTC USD
			 */
//			StringBuffer response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/ticker/btcusd/", "balance");
//			logger.info("balance : " + response.toString());

			/*
				balance
		 	*/
			StringBuffer response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/balance/", "balance");
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

			profit = depositAmount_usd.add(withDrawAmount_usd);
			profit = profit.add(depositAmount_eur.add(withDrawAmount_eur));
			profit = new BigDecimal(balance.getUsd_available()).subtract(profit);

			profit = profit.setScale(2, RoundingMode.CEILING);


			model.addAttribute("depositAmountUsd", depositAmount_usd.toString());
			model.addAttribute("depositAmountEur", depositAmount_eur.toString());
			model.addAttribute("buyAmount", buyAmount.toString());
			model.addAttribute("sellAmount", sellAmount.toString());
			model.addAttribute("withDrawAmountUsd", withDrawAmount_usd.toString());
			model.addAttribute("withDrawAmountEur", withDrawAmount_eur.toString());
			model.addAttribute("profitAmount", profit.toString());

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
