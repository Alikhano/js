package ru.alikhano.cyberlife.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminSeleniumTest {

	private WebDriver driver;

	private WebDriverWait driverWait;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/alikhano/chromedriver_win32/chromedriver.exe");
		System.setProperty("webdriver.edge.drive", "C:/Windows/System32/MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
		driver.get("http://localhost:9999/cyberlife/login");
		driverWait = new WebDriverWait(driver, 15);

		// login
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("admin");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("1234");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button"))).click();
	}

	@After
	public void close() {
		// logout
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout"))).click();
		driver.quit();
	}

	@Test
	@Ignore
	public void adminTest() {
		successfullyEditProduct();
		tryToAddDuplicateCategoryAndFail();
		tryToAddDuplicateConfigAndFail();
		tryToUpdateOrderStatusAndFail();
	}

	private void successfullyEditProduct() {
		WebElement inventoryElement = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("inventory")));
		new Actions(driver).moveToElement(inventoryElement).click().perform();
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("edit-button"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitsInStock"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("unitsInStock"))).sendKeys("10");
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button"))).submit();
	}

	private void tryToAddDuplicateCategoryAndFail() {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-category"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("catType"))).sendKeys("education");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-category"))).submit();
	}

	private void tryToAddDuplicateConfigAndFail() {
		driverWait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("add-cons"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("level"))).sendKeys("high");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("description"))).sendKeys("test");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-cons"))).submit();
	}

	private void tryToUpdateOrderStatusAndFail() {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("order-status"))).click();

		Select selectOrder = new Select(driver.findElement(By.id("orderStatus")));
		selectOrder.selectByValue("awaits delivery");
		Select selectPayment = new Select(driver.findElement(By.id("paymentStatus")));
		selectPayment.selectByValue("unpaid");

		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-order-change"))).submit();
	}
}
