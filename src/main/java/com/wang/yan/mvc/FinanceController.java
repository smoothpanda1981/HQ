package com.wang.yan.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@Controller
@RequestMapping("/finance")
public class FinanceController {
	private static final Logger logger = Logger.getLogger(FinanceController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) {
		model.addAttribute("message", "Stock Exchange");
		Stock stock = null;
		try {
			stock = YahooFinance.get("INTC");
			BigDecimal price = stock.getQuote().getPrice();
			BigDecimal change = stock.getQuote().getChangeInPercent();
			BigDecimal peg = stock.getStats().getPeg();
			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

			model.addAttribute("stock_name", stock.getName());
			model.addAttribute("stock_currency", stock.getCurrency());
			model.addAttribute("stock_day_high", stock.getQuote().getDayHigh());
			model.addAttribute("stock_day_low", stock.getQuote().getDayLow());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "finance";
	}
}