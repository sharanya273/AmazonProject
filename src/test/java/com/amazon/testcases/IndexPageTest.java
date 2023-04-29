package com.amazon.testcases;

import com.amazon.base.BaseClass;
import com.amazon.pageobjects.IndexPage;
import com.amazon.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IndexPageTest extends BaseClass {
    IndexPage indexPage;
    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity"})
    public void setup(String browser) {
        launchApp(browser);
    }
    @AfterMethod(groups={"Smoke","Sanity"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups="Sanity")
    public void verifyLogoTest(){
        indexPage = new IndexPage();
       // Log.info("validate the log");
        boolean result = indexPage.validateLogo();
        Assert.assertTrue(result);
     //   Log.endTestCase("verifyLogo");


    }
    @Test(groups="Sanity")
    public void verifyTitleTest(){
        Log.startTestCase("verifyTitleTest");
        String actTitle = indexPage.getAmazonTitle();
        Log.info("verifying that the title matches");
        Assert.assertEquals(actTitle,"Amazon.com. Spends less. Smile more.");
       Log.endTestCase("verifyTitleTest");
    }

}
