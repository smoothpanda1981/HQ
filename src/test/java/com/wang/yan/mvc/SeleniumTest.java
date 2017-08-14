package com.wang.yan.mvc;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SeleniumTest {
    @Test
    public void testFedexBilling() throws InterruptedException {
        int nbOfLoop = 0;
        List<Double> doubleList = new ArrayList<Double>();

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--no-startup-window");

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
    }
}
