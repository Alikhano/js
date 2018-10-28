package ru.alikhano.cyberlife.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminSeleniumTest {
	
	WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/alikhano/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("http://localhost:8080/cyberlife/login");
	}
	
	@Test
	public void adminTest() throws InterruptedException {
		WebDriverWait driverWait = new WebDriverWait(driver, 1000);
		
		// login
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("admin");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("admin");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div/form/div[4]/div[2]/button"))).click();
		
		
		// edit product
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[3]/a"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-sidebar\"]/div/div/div/a[2]"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-catalogue\"]/tbody/tr[1]/td[7]/a[1]/input"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"unitsInStock\"]"))).clear();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"unitsInStock\"]"))).sendKeys("10");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"editProduct\"]/div/input"))).click();
			
		//  update order status
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-sidebar\"]/div/div/div/a[6]"))).click();
		
		Select selectOrder = new Select(driver.findElement(By.xpath("//*[@id=\"orderStatus\"]")));
		selectOrder.selectByValue("delivered, awaits pickup");
		
		Select selectPayment = new Select(driver.findElement(By.xpath("//*[@id=\"paymentStatus\"]")));
		selectPayment.selectByValue("unpaid");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"update\"]"))).click();
		

		
		// try to add category
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-sidebar\"]/div/div/div/a[4]"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"catType\"]"))).sendKeys("education");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"addCat\"]/div/input"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[4]/div/button"))).click();
		

		// try to add AI config
		
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"admin-sidebar\"]/div/div/div/a[5]"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"level\"]"))).sendKeys("high AI");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"description\"]"))).sendKeys("test");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"addCons\"]/div/input"))).click();
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[4]/div/button"))).click();
		
		//  logout	
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[6]/a"))).click();
		
		
	}
	
	@After
	public void close() {
		driver.quit();
	}

}
