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


			int nbOfLoop = 0;
			List<Double> doubleList = new ArrayList<Double>();

			// stored in /opt/tomcat/bin/chromedriver
			System.setProperty("webdriver.chrome.driver", "/opt/tomcat/bin/chromedriver");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("disable-infobars");
//			options.addArguments("no-sandbox");
//			options.addArguments("--no-startup-window");
//			options.addArguments("disable-setuid-sandbox");
//			options.addArguments("allow-insecure-localhost");

			WebDriver driver = new ChromeDriver();
			// open the browser and go to JavaTutorial Network Website
			driver.get("https://www.fedex.com/apps/myprofile/loginandcontact/?locale=en_ch&cntry_code=ch");
			Thread.sleep(4000L);
			driver.findElement(By.xpath("//input[@name='USER']")).sendKeys("tjm2016");
			driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys("Goodfortune2016");
			driver.findElement(By.xpath("//button[@name='login']")).click();
			Thread.sleep(8000L);
			System.out.println("Go");

			WebElement element = driver.findElement(By.id("tab2menu1"));
			WebElement element2 = element.findElement(By.xpath(".//li[@id='0']/a"));
			System.out.println(element2.getAttribute("class"));
			System.out.println(element2.getAttribute("Target"));
			System.out.println(element2.getAttribute("href"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element2);
			Thread.sleep(1000L);

			WebElement element3 = driver.findElement(By.xpath("//div[contains(@id,'paidClosed')]/a"));
			System.out.println(element3.getText());
			executor.executeScript("arguments[0].click()", element3);
			Thread.sleep(5000L);

			WebElement element4 = driver.findElement(By.xpath("//table[@name='mainContentId:invoicesAllOpenTable']/tbody/tr/td[2]"));
			System.out.println(element4.getTagName());
			List<WebElement> element5 = element4.findElements(By.xpath(".//table/tbody/tr/td"));
			System.out.println("element5 has : " + element5.size());
			nbOfLoop = element5.size();


			for(int i = 0; i < nbOfLoop; i++) {
				List<WebElement> element6 = driver.findElements(By.xpath("//tbody[@id='mainContentId:invoicesAllOpenTable:tbody']/tr"));
				System.out.println(element6.size());
				for (int j = 0; j < element6.size(); j++) {
					WebElement webElement2 = element6.get(j);
					System.out.println(webElement2.findElement(By.xpath("./td[11]")).getText());
					String s = webElement2.findElement(By.xpath("./td[11]")).getText();
					s = s.replace(",", "");
					doubleList.add(Double.valueOf(s));
				}

				if ((i+2) <= nbOfLoop ) {
					String s2 = ".//table/tbody/tr/td[" + String.valueOf(i+2) + "]";
					WebElement webElement = element4.findElement(By.xpath(s2));
					WebElement nextElement = webElement.findElement(By.xpath(".//a"));
					System.out.println(nextElement.getAttribute("id"));
					String idValue = nextElement.getAttribute("id");
					String idValue2 = idValue.substring(0, idValue.length()-1);
					System.out.println(idValue2);
					String idValue3 = idValue2 + String.valueOf(i+2);
					System.out.println(idValue3);

					WebElement elementT1 = driver.findElement(By.id(idValue3));
					executor.executeScript("arguments[0].click()", elementT1);
					Thread.sleep(5000L);

				}
			}


			System.out.println("*****************************");
			Double total = 0.00;
			for (Double d : doubleList) {
				System.out.println(d);
				total += d;
			}
			System.out.println("Total = " + total);
			System.out.println("*****************************");

			driver.quit();
			model.addAttribute("fedexBillingTotal", total.toString());
			Double fourPercent = total * 0.04 * 0.49;
			model.addAttribute("fourPercent", fourPercent);

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
