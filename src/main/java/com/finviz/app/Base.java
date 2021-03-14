package com.finviz.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	protected WebDriver driver;

	protected void set_driver(String browser) {
		switch (browser) {
		case "chrome":
			ChromeOptions option = new ChromeOptions();
			option.addArguments("user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome/User Data");//run chrome with profile
			//option.addArguments("-start-maximized");
			option.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "C:/Tools/chromedriver.exe");			
			this.driver = new ChromeDriver(option);
			break;
		case "firefox":
			//System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();			
			this.driver = new FirefoxDriver();
			break;
		default:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=src/main/resources/Profiles/ChromeProfile");
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "C:/Tools/chromedriver.exe");			
			this.driver = new ChromeDriver(options);
			break;
		}
	}

//go to url
	protected void go_to_url(String url) {
		this.driver.get(url);
	}

	// explicit wait
	protected void wait(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void pause() {
		try {
			this.driver.wait(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
