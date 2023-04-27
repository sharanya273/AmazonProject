
package com.amazon.testcases;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrokenLinkTest {
    public static void main(String[] args) {
        // Launch the web page in a Chrome browser
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(ops);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.mandoemedia.com/en-au");
        driver.manage().window().maximize();
        // Find all links on the page
        String url="";
        List<WebElement> allURLs = driver.findElements(By.tagName("a"));
        System.out.println("Total links on the Wb Page: " + allURLs.size());

        //We will iterate through the list and will check the elements in the list.
        Iterator<WebElement> iterator = allURLs.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }

        //Close the browser session
        driver.quit();
    }
}

