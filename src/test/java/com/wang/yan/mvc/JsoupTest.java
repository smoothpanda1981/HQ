package com.wang.yan.mvc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

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
