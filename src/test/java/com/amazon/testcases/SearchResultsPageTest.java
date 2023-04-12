package com.amazon.testcases;

import com.amazon.base.BaseClass;
import com.amazon.pageobjects.IndexPage;
import com.amazon.pageobjects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchResultsPageTest extends BaseClass {
     IndexPage indexPage;
     SearchResultsPage searchResultPage;
    @BeforeMethod
    public void setup() {

        launchApp();
    }
    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }


    @Test
    public void productAvailabilityTest() throws Throwable {

        //Log.startTestCase("productAvailabilityTest");
        indexPage= new IndexPage();
        searchResultPage=indexPage.searchProduct("flower");
        boolean result=searchResultPage.isProductAvailable();
        Assert.assertTrue(result);
       // Log.endTestCase("productAvailabilityTest");
    }

}
