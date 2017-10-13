package com.wang.yan.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.bitstamp.Balance;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Created by ywang on 10.04.16.
 */
public class JsoupTest {
    @Test
    public void testJsoupOnFedexBillingLoginPage() throws IOException {
        Document doc = Jsoup.connect("http://www.fedex.com/us/fcl/pckgenvlp/online-billing/").get();
        System.out.println(doc.title());
        Elements elements = doc.getElementsByTag("div");
        for (Element src : elements) {
            if (src.tagName().equals("input")) {
                System.out.println(src.attr("name"));
            }
        }
        System.out.println("************************************");
        System.out.println(doc.outerHtml());

        System.out.println("************************************");
    }
}
