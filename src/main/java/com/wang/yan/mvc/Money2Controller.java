package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.BitstampProfit;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.bitstamp.*;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.service.FedexService;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.jvm.hotspot.runtime.linux_aarch64.LinuxAARCH64JavaThreadPDAccess;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

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
				balance
		 	*/
			StringBuffer response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/balance/", "balance");
			logger.info("balance : " + response.toString());
			model.addAttribute("balance_post_data_string", BitstampUtils.post_data_string);
			Balance balance = mapper.readValue(response.toString(), Balance.class);
			model.addAttribute("btc_balance", balance.getBtc_balance());
			model.addAttribute("eth_balance", balance.getEth_balance());
			model.addAttribute("usd_available", balance.getUsd_available());
			model.addAttribute("eur_available", balance.getEur_available());

			/*
			Convert EUR to USD

			Fedex, only keep account number and amount with 4% or 5%
			 */
			response = bitstampUtils.getGetData("https://www.bitstamp.net/api/eur_usd/", "conversion_eur_usd");
			logger.info("Conversion rate eur to usd : " + response.toString());
			ConversionRateEURToUSD conversionRateEURToUSD = mapper.readValue(response.toString(), ConversionRateEURToUSD.class);

			/*
				user_transaction
		 	*/
			response = bitstampUtils.getPostData("https://www.bitstamp.net/api/v2/user_transactions/", "user_transaction");
			logger.info("user_transactions : " + response.toString());
			model.addAttribute("user_transactions_post_data_string", BitstampUtils.post_data_string);

			List<UserTransaction2> userTransactionList = mapper.readValue(response.toString(), new TypeReference<List<UserTransaction2>>(){});



			BigDecimal depositUSD = computeDeposit(userTransactionList, "USD");
			BigDecimal depositEUR = computeDeposit(userTransactionList, "EUR");
			BigDecimal withdrawUSD = computeWithdraw(userTransactionList, "USD");
			BigDecimal withdrawEUR = computeWithdraw(userTransactionList, "EUR");

			model.addAttribute("depositUSD", depositUSD.toString());
			model.addAttribute("depositEUR", depositEUR.toString());
			model.addAttribute("withdrawUSD", withdrawUSD.toString());
			model.addAttribute("withdrawEUR", withdrawEUR.toString());



			BigDecimal totalDepositUSD = new BigDecimal(0);
			totalDepositUSD = totalDepositUSD.add(depositUSD).add(withdrawUSD);
			totalDepositUSD = totalDepositUSD.setScale(2, RoundingMode.CEILING);
			BigDecimal totalDepositEUR = new BigDecimal(0);
			totalDepositEUR = totalDepositEUR.add(depositEUR).add(withdrawEUR);
			totalDepositEUR = totalDepositEUR.setScale(2, RoundingMode.CEILING);
			BigDecimal totalDepositEURConvToUSD = new BigDecimal(0);
			totalDepositEURConvToUSD = totalDepositEUR.multiply(new BigDecimal(conversionRateEURToUSD.getSell()));
			totalDepositEURConvToUSD = totalDepositEURConvToUSD.setScale(2, RoundingMode.CEILING);

			model.addAttribute("totalDepositUSD", totalDepositUSD.toString());
			model.addAttribute("totalDepositEUR", totalDepositEUR.toString());
			BigDecimal totalDeposit = new BigDecimal(0);
			totalDeposit = totalDeposit.add(totalDepositUSD).add(totalDepositEURConvToUSD);
			totalDeposit = totalDeposit.setScale(2, RoundingMode.CEILING);
			model.addAttribute("totalDeposit", totalDeposit.toString());



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


			BigDecimal total_BTC_USD = new BigDecimal(0);
			total_BTC_USD = total_BTC_USD.add(buyBTC_USD).add(sellBTC_USD);
			total_BTC_USD = total_BTC_USD.setScale(2, RoundingMode.CEILING);
			BigDecimal total_ETH_USD = new BigDecimal(0);
			total_ETH_USD = total_ETH_USD.add(buyETH_USD).add(sellETH_USD);
			total_ETH_USD = total_ETH_USD.setScale(2, RoundingMode.CEILING);
			BigDecimal total_XRP_USD = new BigDecimal(0);
			total_XRP_USD = total_XRP_USD.add(buyXRP_USD).add(sellXRP_USD);
			total_XRP_USD = total_XRP_USD.setScale(2, RoundingMode.CEILING);

			BigDecimal total_BTC_EUR = new BigDecimal(0);
			total_BTC_EUR = total_BTC_EUR.add(buyBTC_EUR).add(sellBTC_EUR);
			total_BTC_EUR = total_BTC_EUR.setScale(2, RoundingMode.CEILING);
			BigDecimal total_BTC_EURConvToUSD = new BigDecimal(0);
			total_BTC_EURConvToUSD = total_BTC_EUR.multiply(new BigDecimal(conversionRateEURToUSD.getSell()));
			total_BTC_EURConvToUSD = total_BTC_EURConvToUSD.setScale(2, RoundingMode.CEILING);


			model.addAttribute("total_BTC_USD", total_BTC_USD.toString());
			model.addAttribute("total_BTC_EUR", total_BTC_EUR.toString());
			model.addAttribute("total_ETH_USD", total_ETH_USD.toString());
			model.addAttribute("total_XRP_USD", total_XRP_USD.toString());


			BigDecimal totalBTC = new BigDecimal(0);
			totalBTC = totalBTC.add(total_BTC_USD).add(total_BTC_EURConvToUSD).add(total_ETH_USD).add(total_XRP_USD);
			totalBTC = totalBTC.setScale(2, RoundingMode.CEILING);
			model.addAttribute("totalBTC", totalBTC.toString());


			Map<String, List<UserTransaction2>> userTransaction2ListAndMap = new HashMap<>();
			for (UserTransaction2 userTransaction2 : userTransactionList) {
				String[] date2 = userTransaction2.getDatetime().split(" ");
				String[] date3 = date2[0].split("-");
				String finalS = date3[0] + "-" + date3[1];
				if (!userTransaction2ListAndMap.containsKey(finalS)){
					List<UserTransaction2> emptyUserTransation2List = new ArrayList<>();
					userTransaction2ListAndMap.put(finalS, emptyUserTransation2List);
				}
			}

			for (UserTransaction2 userTransaction2 : userTransactionList) {
				String[] date2 = userTransaction2.getDatetime().split(" ");
				String[] date3 = date2[0].split("-");
				String finalS = date3[0] + "-" + date3[1];
				userTransaction2ListAndMap.get(finalS).add(userTransaction2);
			}

			Map<String, List<UserTransactionForTable>> map2 = new HashMap<>();
			for (String s : userTransaction2ListAndMap.keySet()) {
				List<UserTransactionForTable> userTransactionForTableList = convertUserTransation2ListIntoUserTransactionForTableList(userTransaction2ListAndMap.get(s));
				map2.put(s, userTransactionForTableList);
			}


			for (String s : userTransaction2ListAndMap.keySet()) {
				logger.info("temp.s : " + s);
				List<UserTransactionForTable> temp = map2.get(s);
				for (UserTransactionForTable temp2 : temp) {
					logger.info("temp2.date : " + temp2.getDatetime());
					logger.info("temp2.deposit : " + temp2.getDeposit());
					logger.info("temp2.withdraw : " + temp2.getWithdraw());
					logger.info("temp2.buy : " + temp2.getBuy());
					logger.info("temp2.sell : " + temp2.getSell());
				}
			}
			logger.info("size of map2 : " + map2.size());
			model.addAttribute("map2", map2);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "money2";
	}

	private List<UserTransactionForTable> convertUserTransation2ListIntoUserTransactionForTableList(List<UserTransaction2> UserTransaction2List) {
		List<UserTransactionForTable> result = new ArrayList<>();

		for (UserTransaction2 userTransaction2 : UserTransaction2List) {

			UserTransactionForTable userTransactionForTable = new UserTransactionForTable();
			userTransactionForTable.setDatetime(userTransaction2.getDatetime());

			if (userTransaction2.getType().equals("0")) {
				if (userTransaction2.getUsd() >= 0.0) {
					userTransactionForTable.setDeposit(userTransaction2.getUsd());
				} else if (userTransaction2.getEur() >= 0.0) {
					userTransactionForTable.setDeposit(userTransaction2.getEur());
				}
			} else if (userTransaction2.getType().equals("1")) {
				if (userTransaction2.getUsd() <= 0.0) {
					userTransactionForTable.setWithdraw(userTransaction2.getUsd());
				} else if (userTransaction2.getEur() <= 0.0) {
					userTransactionForTable.setWithdraw(userTransaction2.getEur());
				}
			} else if (userTransaction2.getType().equals("2")) {
				if (userTransaction2.getBtc_usd() != null && userTransaction2.getBtc() > 0.0) {
					userTransactionForTable.setBuy(userTransaction2.getUsd());
				} else if (userTransaction2.getBtc_eur() != null && userTransaction2.getBtc() > 0.0) {
					userTransactionForTable.setBuy(userTransaction2.getEur());
				} else if (userTransaction2.getEth_usd() != null && userTransaction2.getEth() > 0.0) {
					userTransactionForTable.setBuy(userTransaction2.getUsd());
				} else if (userTransaction2.getXrp_usd() != null && userTransaction2.getXrp() > 0.0) {
					userTransactionForTable.setBuy(userTransaction2.getUsd());
				}

				if (userTransaction2.getBtc_usd() != null && userTransaction2.getBtc() < 0.0) {
					userTransactionForTable.setSell(userTransaction2.getUsd());
				} else if (userTransaction2.getBtc_eur() != null && userTransaction2.getBtc() < 0.0) {
					userTransactionForTable.setSell(userTransaction2.getEur());
				} else if (userTransaction2.getEth_usd() != null && userTransaction2.getEth() < 0.0) {
					userTransactionForTable.setSell(userTransaction2.getUsd());
				} else if (userTransaction2.getXrp_usd() != null && userTransaction2.getXrp() < 0.0) {
					userTransactionForTable.setSell(userTransaction2.getUsd());
				}
			}
			result.add(userTransactionForTable);
		}
		return result;
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
		BigDecimal fees = new BigDecimal(0);
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
				fees = fees.add(new BigDecimal(userTransaction.getFee()));
			}
		}
		fees = fees.multiply(new BigDecimal(-1));
		withdraw = withdraw.add(fees);
		withdraw = withdraw.setScale(2, RoundingMode.CEILING);
		return withdraw;
	}

	private BigDecimal computeBuy(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal buy = new BigDecimal(0);
		BigDecimal fees = new BigDecimal(0);
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
				fees = fees.add(new BigDecimal(userTransaction.getFee()));
			}
		}
		fees = fees.multiply(new BigDecimal(-1));
		buy = buy.add(fees);
		buy = buy.setScale(2, RoundingMode.CEILING);
		return buy;
	}

	private BigDecimal computeSell(List<UserTransaction2> userTransaction2List, String currency) {
		BigDecimal sell = new BigDecimal(0);
		BigDecimal fees = new BigDecimal(0);
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
				fees = fees.add(new BigDecimal(userTransaction.getFee()));
			}
		}
		fees = fees.multiply(new BigDecimal(-1));
		sell = sell.add(fees);
		sell = sell.setScale(2, RoundingMode.CEILING);
		return sell;
	}
}