package com.amazon.pageobjects;

import com.amazon.actiondriver.Action;
import com.amazon.base.BaseClass;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

    @FindBy(xpath = "//h1[normalize-space()='Sign in']")
    WebElement signInTxt;
    @FindBy(id = "ap_email")
    WebElement email;

    @FindBy(id = "continue")
    WebElement continueBtn;
    @FindBy(id = "ap_password")
    WebElement password;

    @FindBy(id = "signInSubmit")
    WebElement signInSubmit;



    public LoginPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public YourAccountPage login(String mail, String pswd,YourAccountPage yourAccountPage){
        Action.type(email,mail);
        Action.explicitWait(getDriver(),continueBtn,30);
        Action.click(getDriver(),continueBtn);
        Action.explicitWait(getDriver(),password,30);
        Action.type(password,pswd);
        Action.click(getDriver(),signInSubmit);

        yourAccountPage=new YourAccountPage();
        return yourAccountPage;

    }
    public boolean validateSignin() throws Throwable {
        Action.fluentWait(getDriver(), signInTxt, 20);
        return Action.isDisplayed(getDriver(), signInTxt);
    }

}
