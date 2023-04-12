package com.amazon.testcases;

import com.amazon.base.BaseClass;
import com.amazon.pageobjects.IndexPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IndexPageTest extends BaseClass {
    IndexPage indexPage;
    @BeforeMethod
    public void setup() {
        launchApp();
    }
    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }

    @Test
    public void verifyLogoTest(){
        indexPage = new IndexPage();
       // Log.info("validate the log");
        boolean result = indexPage.validateLogo();
        Assert.assertTrue(result);
     //   Log.endTestCase("verifyLogo");


    }
    @Test
    public void verifyTitleTest(){
       // Log.debug("Hai dfkdjj==================");
        String actTitle = indexPage.getAmazonTitle();
        Assert.assertEquals(actTitle,"Amazon.com. Spend less. Smile more.");
       // Log.error("test fials display this");
    }

}
