package com.bitc.java404;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Nate extends WebBase {
  private String nateUrl = "https://www.nate.com/";
  private WebDriver driver;
  private ChromeOptions options;

  public Nate() {
    System.setProperty(WebBase.CHROME_DRIVER_ID, WebBase.CHROME_DRIVER_PATH);
    options = optionSetup();
    driver = new ChromeDriver(options);
  }

  public ChromeOptions optionSetup() {
    ChromeOptions opt = new ChromeOptions();
    opt.addArguments("--start-maximized");
    opt.addArguments("--disable-popup-blocking");
    opt.addArguments("--remote-allow-origins=*");

    return opt;
  }

  public void login() throws Exception {
    driver.get(nateUrl);
    Thread.sleep(3000);

    WebElement loginId = driver.findElement(By.id("ID"));
    loginId.sendKeys("idididid");
    Thread.sleep(2000);

    WebElement loginPw = driver.findElement(By.id("PASSDM"));
    loginPw.sendKeys("passpasspass");
    Thread.sleep(2000);

    WebElement loginBtn = driver.findElement(By.id("btnLOGIN"));
    loginBtn.click();
    Thread.sleep(5000);

    driver.quit();
  }
}











