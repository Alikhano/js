package ru.alikhano.cyberlife.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserSeleniumTest {

	private WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/alikhano/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("http://localhost:9999/cyberlife/login");
	}

	@Test
	public void userPurchaseTest() {
		WebDriverWait driverWait = new WebDriverWait(driver,1000);

		// login

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("user2");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("admin");

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div/form/div[4]/div[2]/button")))
				.click();

		// go to catalogue, select product

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/a")))
				.click();

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"admin-catalogue_paginate\"]/ul/li[3]/a")))
				.click();
		

		// add product to cart

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"admin-catalogue\"]/tbody/tr[5]/td[2]/a")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"quantity\"]"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"quantity\"]"))).sendKeys("2");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"addToCart\"]"))).click();
		
		// open cart, proceed to order

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[4]/a")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/a[1]"))).click();
		
		// submit order

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"newOrder\"]/input[2]")))
				.click();

		// log out

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[6]/a")))
				.click();
		

	}

	@Test
	public void editAccountTest() {

		WebDriverWait driverWait = new WebDriverWait(driver, 1000);

		// login

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("user2");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("admin");

		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div/form/div[4]/div[2]/button")))
				.click();
		

		// access account

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[3]/a")))
				.click();

		// edit personal info

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/p[7]/a[2]")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"birthDate\"]"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"birthDate\"]")))
				.sendKeys("1994-10-27");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"updateProfile\"]/input[2]")))
				.click();
		

		// edit address

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div/p[5]/a")))
				.click();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"city\"]"))).clear();

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"city\"]"))).sendKeys("Paris");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"changeAdr\"]/input[2]")))
				.click();
		
		// log out

		driverWait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[6]/a")))
				.click();


	}

	@After
	public void close() {
		driver.quit();
	}

}
