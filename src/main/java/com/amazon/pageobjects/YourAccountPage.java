package com.amazon.pageobjects;

import com.amazon.actiondriver.Action;
import com.amazon.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAccountPage extends BaseClass {
    @FindBy(xpath="//a[normalize-space()='Click here to go to amazon.in']")
    private WebElement yourAccountTxt;





    public YourAccountPage(){
        PageFactory.initElements(getDriver(),this);
    }

    public void waitForAccountpage(){
        Action.explicitWait(getDriver(),yourAccountTxt,30);
    }
}

