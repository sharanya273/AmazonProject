package com.amazon.pageobjects;

import com.amazon.actiondriver.Action;
import com.amazon.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends BaseClass {
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchBox;
    @FindBy(xpath = "//a[@id='nav-logo-sprites']")
    WebElement logoTxt;
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement searchSubmitBtn;
    @FindBy(id="nav-link-accountList")
    WebElement accountnLists;

    @FindBy(id="nav-link-accountList")
    WebElement signInBtn;
    @FindBy(xpath = "//span[contains(text(),'Sign Out')]")
    WebElement signOut;

    public IndexPage(){
        PageFactory.initElements(getDriver(), this);
    }


    public LoginPage clickOnSignIn() throws Throwable {
        Action.click(getDriver(),signInBtn);
        Thread.sleep(3000);
        return new LoginPage();

    }
    public boolean validateLogo(){
        return Action.isDisplayed(getDriver(),logoTxt);
    }
    public String getAmazonTitle(){
        String amazonTitle = getDriver().getTitle();
        return amazonTitle;
    }
    public void moveToAccountList(){

        Action.mouseOverElement(getDriver(),accountnLists);
    }
    public void moveTosiginOut(){

        Action.mouseOverElement(getDriver(),accountnLists);
        Action.mouseOverElement(getDriver(),signOut);
        Action.click(getDriver(),signOut);
    }
    public SearchResultsPage searchProduct(String productName) throws Throwable {
        Action.fluentWait(getDriver(),searchBox,20);
        Action.type(searchBox, productName);
        Action.click(getDriver(), searchSubmitBtn);
        return new SearchResultsPage();
    }

}
