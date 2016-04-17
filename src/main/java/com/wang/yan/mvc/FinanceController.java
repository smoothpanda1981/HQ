package com.wang.yan.mvc;

import com.wang.yan.mvc.model.finance.FxData;
import com.wang.yan.mvc.model.finance.StockData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/finance")
public class FinanceController {
	private static final Logger logger = Logger.getLogger(FinanceController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) {
		model.addAttribute("message", "Stock Exchange");
		Stock stock = null;
		try {
			List<StockData> stockDataList = new ArrayList<StockData>();
			String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
			Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
			for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
				StockData financeData = new StockData();
				financeData.setStockName(entry.getValue().getName());
				financeData.setStockCurrency(entry.getValue().getCurrency());
				financeData.setStockDayHigh(entry.getValue().getQuote().getDayHigh());
				financeData.setStockDayLow(entry.getValue().getQuote().getDayLow());

				stockDataList.add(financeData);
			}
			model.addAttribute("stockDataList", stockDataList);


			List<FxData> fxDataList = new ArrayList<FxData>();
			String[] fxSymbols = new String[] {"USDCHF=X", "USDGBP=X", "USDEUR=X", "CHFUSD=X", "GBPUSD=X", "EURUSD=X"};
			Map<String, FxQuote> forexes = YahooFinance.getFx(fxSymbols);
			for (Map.Entry<String, FxQuote> fxQuoteEntry : forexes.entrySet()) {
				FxData fxData = new FxData();
				fxData.setFxSymbol(fxQuoteEntry.getValue().getSymbol());
				fxData.setFxPrice(fxQuoteEntry.getValue().getPrice());

				fxDataList.add(fxData);
			}
			model.addAttribute("fxDataList", fxDataList);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "finance";
	}
}