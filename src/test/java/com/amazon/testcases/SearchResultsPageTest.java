package com.amazon.testcases;

import com.amazon.base.BaseClass;
import com.amazon.pageobjects.IndexPage;
import com.amazon.pageobjects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchResultsPageTest extends BaseClass {
     IndexPage indexPage;
     SearchResultsPage searchResultPage;
    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity"})
    public void setup(String browser) {
        launchApp(browser);
    }
    @AfterMethod(groups={"Smoke","Sanity"})
    public void tearDown(){
        getDriver().quit();
    }


    @Test(groups="Smoke")
    public void productAvailabilityTest() throws Throwable {

        //Log.startTestCase("productAvailabilityTest");
        indexPage= new IndexPage();
        searchResultPage=indexPage.searchProduct("flower");
        boolean result=searchResultPage.isProductAvailable();
        Assert.assertTrue(result);
       // Log.endTestCase("productAvailabilityTest");
    }

}
