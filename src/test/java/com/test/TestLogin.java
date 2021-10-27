package com.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.utils.ReadConfig;


public class TestLogin extends BaseTest {

    ReadConfig readconfig=new ReadConfig();

    public String baseURL=readconfig.getApplicationURL();
    public String userName=readconfig.getUsername();
    public String password=readconfig.getPassword();


    static Logger log = Logger.getLogger(TestLogin.class.getName());


    @Test
    public void loginTest() {


        test = extent.createTest("loginTest");
        String expcetedTitle = "asdf";

        driver.get(baseURL);
        LoginPage lp = new LoginPage(driver);
        log.info("Sending user name: " + userName + " and password");
        log.info("Clicking on login button");
        lp.doLogin(userName, password);

        log.info("Checking the user logged in Name");
        String actualTitle = lp.getLoggedUsersName();

        log.info("Comparing Expected and Actual User");
        validateExpectedToActual(expcetedTitle, actualTitle);
    }


    @Test(enabled = true)
    public void invalidLoginTest() {

        test = extent.createTest("invalidLoginTest");
        driver.get(baseURL);
        LoginPage lp = new LoginPage(driver);
        log.info("Sending invalid credentials");
        lp.doLogin("asd", "asd");

        WebElement failElem = driver.findElement(By.xpath("//*[@id=\"output\"]/div"));
        String errorMsg= failElem.getText();
        if(errorMsg.equals("Invalid username or password!")){
            Assert.fail("Invalid Credentials passed");
        }else{
            log.info("Invalid Login cred error check was skipped!");
        }

    }


}
