package shilpaamshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import shilpaamshettyacademy.TestComponents.BaseTest;
import shilpaamshettyacademy.pageobjects.CartPage;
import shilpaamshettyacademy.pageobjects.CheckoutPage;
import shilpaamshettyacademy.pageobjects.ConfirmationPage;
import shilpaamshettyacademy.pageobjects.LandingPage;
import shilpaamshettyacademy.pageobjects.OrderPage;
import shilpaamshettyacademy.pageobjects.ProductCatalogue;
import org.openqa.selenium.TakesScreenshot;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(country);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(5000,5000)");
		Thread.sleep(2000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String msg = confirmationPage.getConfimationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingpage.loginApplication("shilpaam55@gmail.com", "Qwerty1!");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	//Extent Reports

	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "shilpaam55@gmail.com"); map.put("password", "Qwerty1!"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "shilpaam55@gmail.com"); map1.put("password", "Qwerty1!");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 */
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\shilpashettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	// @DataProvider
	// public Object[][] getData() {
	// return new Object[][] { { "shilpaam55@gmail.com","Qwerty1!","ZARA COAT 3" },
	// { "shilpaam55@gmail.com","Qwerty1!","ADIDAS ORIGINAL" } };}

}
