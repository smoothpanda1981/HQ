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
				balance
		 	*/
			StringBuffer response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/balance/", "balance");
			logger.info(response.toString());
			Balance balance = mapper.readValue(response.toString(), Balance.class);
			model.addAttribute("btc_balance", balance.getBtc_balance());
			model.addAttribute("usd_available", balance.getUsd_available());

			/*
				user_transaction
		 	*/
			response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/user_transactions/", "user_transaction");
			logger.info(response.toString());
			List<UserTransaction> userTransactionList = mapper.readValue(response.toString(), new TypeReference<List<UserTransaction>>(){});
			logger.info("test 1: " + userTransactionList.get(0).getBtc());

			BigDecimal depositAmount = new BigDecimal(0);

			BigDecimal buyAmount = new BigDecimal(0);

			BigDecimal sellAmount = new BigDecimal(0);

			BigDecimal withDrawAmount = new BigDecimal(0);

			for (UserTransaction userTransaction : userTransactionList) {
				if (userTransaction.getType().equals("0")) {
					BigDecimal userTransUsd = new BigDecimal(userTransaction.getUsd());
					logger.info("deposit : " + userTransaction.getUsd());
					depositAmount = depositAmount.add(userTransUsd);
				}

				if (userTransaction.getType().equals("2")) {
					BigDecimal amountBtcValue = new BigDecimal(userTransaction.getUsd());
					if (userTransaction.getBtc() >= 0.0) {
						logger.info("buy : " + userTransaction.getUsd());
						buyAmount = buyAmount.add(amountBtcValue);
					} else {
						logger.info("sell : " + userTransaction.getUsd());
						sellAmount = sellAmount.add(amountBtcValue);
					}
				}

				if (userTransaction.getType().equals("1")) {
					BigDecimal userTransUsd = new BigDecimal(userTransaction.getUsd());
					logger.info("withdraw : " + userTransaction.getUsd());
					withDrawAmount = withDrawAmount.add(userTransUsd);
				}
			}

			model.addAttribute("depositAmount", depositAmount.toString());
			model.addAttribute("buyAmount", buyAmount.toString());
			model.addAttribute("sellAmount", sellAmount.toString());
			model.addAttribute("withDrawAmount", withDrawAmount.toString());
			model.addAttribute("profitAmount", (buyAmount.add(sellAmount)).toString());

			Document doc = Jsoup.connect("http://www.fedex.com/us/fcl/pckgenvlp/online-billing/").get();
			System.out.println("************************************");
			System.out.println(doc.outerHtml());
			System.out.println("************************************");


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
