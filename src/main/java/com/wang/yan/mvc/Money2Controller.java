package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.BitstampProfit;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.bitstamp.Balance;
import com.wang.yan.mvc.model.bitstamp.Ticker;
import com.wang.yan.mvc.model.bitstamp.UserTransaction;
import com.wang.yan.mvc.model.bitstamp.UserTransaction2;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.service.FedexService;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/money2")
public class Money2Controller {
	private static final Logger logger = Logger.getLogger(Money2Controller.class);

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
				user_transaction
		 	*/
			StringBuffer response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/user_transactions/", "user_transaction");
			logger.info("user_transactions : " + response.toString());
			List<UserTransaction2> userTransactionList = mapper.readValue(response.toString(), new TypeReference<List<UserTransaction2>>(){});

			BigDecimal depositUSD = computeDeposit(userTransactionList, "USD");
			BigDecimal depositEUR = computeDeposit(userTransactionList, "EUR");
			BigDecimal withdrawUSD = computeWithdraw(userTransactionList, "USD");
			BigDecimal withdrawEUR = computeWithdraw(userTransactionList, "EUR");

			model.addAttribute("depositUSD", depositUSD.toString());
			model.addAttribute("depositEUR", depositEUR.toString());
			model.addAttribute("withdrawUSD", withdrawUSD.toString());
			model.addAttribute("withdrawEUR", withdrawEUR.toString());

			BigDecimal buyBTC_USD = computeBuy(userTransactionList, "btc_USD");
			BigDecimal buyBTC_EUR = computeBuy(userTransactionList, "btc_EUR");
			BigDecimal buyETH_USD = computeBuy(userTransactionList, "eth_USD");
			BigDecimal buyXRP_USD = computeBuy(userTransactionList, "xrp_USD");

			model.addAttribute("buyBTC_USD", buyBTC_USD.toString());
			model.addAttribute("buyBTC_EUR", buyBTC_EUR.toString());
			model.addAttribute("buyETH_USD", buyETH_USD.toString());
			model.addAttribute("buyXRP_USD", buyXRP_USD.toString());

			BigDecimal sellBTC_USD = computeSell(userTransactionList, "btc_USD");
			BigDecimal sellBTC_EUR = computeSell(userTransactionList, "btc_EUR");
			BigDecimal sellETH_USD = computeSell(userTransactionList, "eth_USD");
			BigDecimal sellXRP_USD = computeSell(userTransactionList, "xrp_USD");

			model.addAttribute("sellBTC_USD", sellBTC_USD.toString());
			model.addAttribute("sellBTC_EUR", sellBTC_EUR.toString());
			model.addAttribute("sellETH_USD", sellETH_USD.toString());
			model.addAttribute("sellXRP_USD", sellXRP_USD.toString());


			BigDecimal buyAmount = new BigDecimal(0);
			BigDecimal sellAmount = new BigDecimal(0);
			BigDecimal profit = new BigDecimal(0);
			BigDecimal multiplicator = new BigDecimal(-1);

//			for (UserTransaction userTransaction : userTransactionList) {
//				if (userTransaction.getType().equals("2")) {
//					BigDecimal amountBtcValue = new BigDecimal(userTransaction.getUsd());
//					BigDecimal amountFeeValue = new BigDecimal(userTransaction.getFee());
//					if (userTransaction.getBtc() < 0.0) {
//						amountBtcValue = amountBtcValue.subtract(amountFeeValue);
//						sellAmount = sellAmount.add(amountBtcValue);
//					} else {
//						amountBtcValue = amountBtcValue.multiply(multiplicator).add(amountFeeValue);
//						buyAmount = buyAmount.add(amountBtcValue);
//					}
//				}
//			}
//
//			buyAmount = buyAmount.setScale(2, RoundingMode.CEILING);
//			sellAmount = sellAmount.setScale(2, RoundingMode.CEILING);
//			model.addAttribute("buyAmount", buyAmount.toString());
//			model.addAttribute("sellAmount", sellAmount.toString());
//
//
//			// Compute profit
//			sellAmount = sellAmount.subtract(buyAmount);
//			profit = profit.add(sellAmount);
//			profit = profit.setScale(2, RoundingMode.CEILING);
//			model.addAttribute("profitAmount", Double.valueOf(profit.toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "money2";
	}

	private BigDecimal computeDeposit(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal deposit = new BigDecimal(0);
		for (UserTransaction2 userTransaction : userTransaction2List) {
			if (userTransaction.getType().equals("0")) {
				if (currency.equals("USD")) {
					if (userTransaction.getUsd() >= 0.0) {
						deposit = deposit.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("EUR")) {
					if (userTransaction.getEur() >= 0.0) {
						deposit = deposit.add(new BigDecimal(userTransaction.getEur()));
					}
				}
			}
		}
		deposit = deposit.setScale(2, RoundingMode.CEILING);
		return deposit;
	}

	private BigDecimal computeWithdraw(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal withdraw = new BigDecimal(0);
		for (UserTransaction2 userTransaction : userTransaction2List) {
			if (userTransaction.getType().equals("1")) {
				if (currency.equals("USD")) {
					if (userTransaction.getUsd() <= 0.0) {
						withdraw = withdraw.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("EUR")) {
					if (userTransaction.getEur() <= 0.0) {
						withdraw = withdraw.add(new BigDecimal(userTransaction.getEur()));
					}
				}
			}
		}
		withdraw = withdraw.setScale(2, RoundingMode.CEILING);
		return withdraw;
	}

	private BigDecimal computeBuy(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal buy = new BigDecimal(0);
		for (UserTransaction2 userTransaction : userTransaction2List) {
			if (userTransaction.getType().equals("2")) {
				if (currency.equals("btc_USD")) {
					if (userTransaction.getBtc_usd() != null && userTransaction.getBtc() > 0.0) {
						buy = buy.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("btc_EUR")) {
					if (userTransaction.getBtc_eur() != null && userTransaction.getBtc() > 0.0) {
						buy = buy.add(new BigDecimal(userTransaction.getEur()));
					}
				} else if (currency.equals("eth_USD")) {
					if (userTransaction.getEth_usd() != null && userTransaction.getEth() > 0.0) {
						buy = buy.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("xrp_USD")) {
					if (userTransaction.getXrp_usd() != null && userTransaction.getXrp() > 0.0) {
						buy = buy.add(new BigDecimal(userTransaction.getUsd()));
					}
				}
			}
		}
		buy = buy.setScale(2, RoundingMode.CEILING);
		return buy;
	}
	private BigDecimal computeSell(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal sell = new BigDecimal(0);
		for (UserTransaction2 userTransaction : userTransaction2List) {
			if (userTransaction.getType().equals("2")) {
				if (currency.equals("btc_USD")) {
					if (userTransaction.getBtc_usd() != null && userTransaction.getBtc() < 0.0) {
						sell = sell.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("btc_EUR")) {
					if (userTransaction.getBtc_eur() != null && userTransaction.getBtc() < 0.0) {
						sell = sell.add(new BigDecimal(userTransaction.getEur()));
					}
				} else if (currency.equals("eth_USD")) {
					if (userTransaction.getEth_usd() != null && userTransaction.getEth() < 0.0) {
						sell = sell.add(new BigDecimal(userTransaction.getUsd()));
					}
				} else if (currency.equals("xrp_USD")) {
					if (userTransaction.getXrp_usd() != null && userTransaction.getXrp() < 0.0) {
						sell = sell.add(new BigDecimal(userTransaction.getUsd()));
					}
				}
			}
		}
		sell = sell.setScale(2, RoundingMode.CEILING);
		return sell;
	}
}