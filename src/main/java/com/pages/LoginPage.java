package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userName")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;


    @FindBy(id="login")
    WebElement login;


    @FindBy(id="userName-value")
    WebElement loggedInUserName;


    public void doLogin(String name, String pwd) {
        userName.sendKeys(name);
        password.sendKeys(pwd);
        login.click();
    }

    public void clickOnLoginBtn(){
        login.click();
    }


    public String getLoggedUsersName(){
        return loggedInUserName.getText();
    }

}
