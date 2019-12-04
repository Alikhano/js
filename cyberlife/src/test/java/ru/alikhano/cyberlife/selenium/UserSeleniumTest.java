package ru.alikhano.cyberlife.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserSeleniumTest {

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
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("test");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("test");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button"))).click();
	}

    @After
    public void close() {
        // logout
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout"))).click();
        driver.quit();
    }

	@Test
	public void userPurchaseTest() {
		addProductToCart();
		submitOrder();
	}

	@Test
	public void editAccountTest() {
		// access account
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-account"))).click();

		editPersonalInfo();
		editAddressInfo();
	}

	private void addProductToCart() {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("catalogue"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"catalogue\"]/tbody/tr[1]/td[2]/a")))
				.click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("quantity"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("quantity"))).sendKeys("2");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addToCart"))).submit();
	}

	private void submitOrder() {
		// open cart, proceed to order
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-cart"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed-order"))).click();
		// submit order
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("submit-order"))).submit();
	}

	private void editPersonalInfo() {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-account"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("birthDate"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("birthDate"))).sendKeys("1994-10-27");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-change"))).click();
	}

	private void editAddressInfo() {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-address"))).click();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("city"))).clear();
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("city"))).sendKeys("Paris");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-change"))).click();
	}
}
