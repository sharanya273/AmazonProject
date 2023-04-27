package com.amazon.testcases;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.time.Duration;
import java.util.Iterator;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrokenLinks {

    public static void main(String[] args) {

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(ops);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://google.com");
        List<WebElement> linksAndImages = driver.findElements(By.tagName("a"));
        linksAndImages.addAll(driver.findElements(By.tagName("img")));

        // Iterate through each link and image
        for (WebElement element : linksAndImages) {
            String url = element.getAttribute("href");
            if (url == null) {
                url = element.getAttribute("src");
            }

            // Skip empty URLs and URLs starting with "javascript:"
            if (url == null || url.isEmpty() || url.startsWith("javascript:")) {
                continue;
            }

            try {
                // Open a connection with the URL and get the response code
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int responseCode = connection.getResponseCode();
                connection.disconnect();

                // If response code is >= 400, then the link or image is broken
                if (responseCode >= 400) {
                    System.out.println("Broken link or image: " + url);
                }
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }}}