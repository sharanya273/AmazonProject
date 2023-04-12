package com.amazon.pageobjects;

import com.amazon.actiondriver.Action;
import com.amazon.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BaseClass {
    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    WebElement productResult;


    public SearchResultsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isProductAvailable() throws Throwable {
        Action.fluentWait(getDriver(),productResult,20);
        return Action.isDisplayed(getDriver(), productResult);
    }

}
