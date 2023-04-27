package com.amazon.base;


import com.amazon.actiondriver.Action;
import com.amazon.utility.ExtentManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;

//    public static WebDriver driver;
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();


    public static WebDriver getDriver() {
        // Get Driver from threadLocalmap
        return driver.get();
    }
    @BeforeSuite(groups={"Smoke","Sanity"})
    public void loadConfig() throws IOException {
        ExtentManager.setExtent();
        DOMConfigurator.configure("log4j.xml");
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + File.separator + "Configuration" +File.separator+ "Config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



@Parameters("browser")
    public static void launchApp(String browserName) {

        //String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--remote-allow-origins=*");
            driver.set(new ChromeDriver(opt));


        } else if (browserName.equalsIgnoreCase("FF")) {
            driver.set(new FirefoxDriver());
        }
        getDriver().manage().window().maximize();
        Action.implicitWait(getDriver(),10);
        Action.pageLoadTimeOut(getDriver(),30);
        getDriver().get(prop.getProperty("url"));



    }



@AfterSuite(groups={"Smoke","Sanity"})
    public void aftersuite(){
        ExtentManager.endReport();
}
}
