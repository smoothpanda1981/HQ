package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.bitstamp.*;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.service.FedexService;
import com.wang.yan.mvc.service.TickerHourService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/graph")
public class GraphController {
	private static final Logger logger = Logger.getLogger(GraphController.class);

	@Autowired
	private TickerHourService tickerHourService;

	@RequestMapping(method = RequestMethod.GET)
	public String moneyPage(ModelMap model) throws InterruptedException {
		model.addAttribute("message", "Graph");

		List<TickerHour> tickerHourList = tickerHourService.getListOfTickerHour();
		List<TickerHour> tickerHourNewList = convertTimestamp(tickerHourList);

		model.addAttribute("data1", convertListToStringData(tickerHourNewList, "last"));
		model.addAttribute("data2", convertListToStringData(tickerHourNewList, "volume"));
//		model.addAttribute("data", "[\n" +
//				"                ['Year', 'Sales', 'Expenses'],\n" +
//				"                ['2004',  1000,      400],\n" +
//				"                ['2005',  1170,      460],\n" +
//				"                ['2006',  660,       1120],\n" +
//				"                ['2007',  1030,      540],\n" +
//				" 				 ['2008',  930,      440]\n" +
//				"            ]");
		return "graph";
	}

	public String convertListToStringData(List<TickerHour> tickerHourList, String var) {
		String firstChar = "[";
		String titleLine = "";
		String lastChar = "]";

		String block = "";
		for (TickerHour tickerHour : tickerHourList) {
			if (var.equals("last")) {
				block = block + "['" + tickerHour.getTimestamp() + "', " + tickerHour.getLast() + "], ";
			} else if (var.equals("volume")) {
				block = block + "['" + tickerHour.getTimestamp() + "', " + tickerHour.getVolume() + "], ";
			} else {
				// do nothing
			}

		}
		block = block.substring(0, block.length()-2);

		if (var.equals("last")) {
			titleLine = "['Time', 'Last'],";
		} else if (var.equals("volume")) {
			titleLine = "['Time', 'Volume'],";
		} else {
			// do nothing
		}
		System.out.println("block : " + block);
		System.out.println("final : " + (firstChar + titleLine + block + lastChar));
		return firstChar + titleLine + block + lastChar;
	}

	public List<TickerHour> convertTimestamp(List<TickerHour> tickerHourList) {
		List<TickerHour> result = new ArrayList<>();
		for (TickerHour tickerHour : tickerHourList) {
			String newTimeStamp = convertUnixTimestampToDate(Long.parseLong(tickerHour.getTimestamp()));
			tickerHour.setTimestamp(newTimeStamp);
			result.add(tickerHour);
		}
		return result;
	}

	private String convertUnixTimestampToDate(Long timstamp) {
		long unixSeconds = timstamp;
		// convert seconds to milliseconds
		Date date = new Date(unixSeconds*1000L);
		// the format of your date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}