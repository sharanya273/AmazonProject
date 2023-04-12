package com.amazon.testcases;

import com.amazon.base.BaseClass;
import com.amazon.pageobjects.IndexPage;
import com.amazon.pageobjects.LoginPage;
import com.amazon.pageobjects.YourAccountPage;
import com.amazon.utility.Log;
import com.amazon.utility.XLUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends BaseClass {
    IndexPage indexPage;
    LoginPage loginPage;
    YourAccountPage yourAccountPage;
    @BeforeMethod
    public void setup() {
        launchApp();
    }
    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }


    @Test(dataProvider = "LoginData")
    public void signInTest(String user,String pwd,String exp) throws Throwable {
        Log.startTestCase("signInTest");
        indexPage = new IndexPage();
        loginPage = new LoginPage();
        indexPage.moveToAccountList();
        Log.info("user is going to click on signin button");
        loginPage = indexPage.clickOnSignIn();
        // yourAccountPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        yourAccountPage = loginPage.login(user,pwd,yourAccountPage);
        Log.info("Verifying if the user is successfully login");
        loginPage.validateSignin();
        Log.info("Login succuess");

        String exp_title="Amazon.com. Spend less. Smile more.";
        String act_title=getDriver().getTitle();
        if(exp.equals("Valid"))
        {
            if(exp_title.equals(act_title))
            {
                indexPage.moveTosiginOut();
                Assert.assertTrue(true);
            }
            else
            {
                Assert.assertTrue(false);
            }
        }
        else if(exp.equals("Invalid"))
        {
            if(exp_title.equals(act_title))
            {
                indexPage.moveTosiginOut();
                Assert.assertTrue(false);
            }
            else
            {
                Assert.assertTrue(true);
            }
        }
    }
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
   // String loginData[][]={
//            {"sharanya.kadarla79@gmail.com","technology$123","Valid"},
//            {"sharanya.kadarla@gmail.com","technology$123","Invalid"}

 //   };
        String path = "/Users/sharanyakadarla/Desktop/AmazonProject/src/main/java/com/amazon/utility/TestDataNew.xlsx";
        XLUtility xlutil=new XLUtility(path);

        int totalrows=xlutil.getRowCount("Credentials");
        int totalcols=xlutil.getCellCount("Credentials",1);

        String loginData[][]=new String[totalrows][totalcols];


        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                loginData[i-1][j]=xlutil.getCellData("Credentials", i, j);
            }

        }
    return loginData;
    }


}
