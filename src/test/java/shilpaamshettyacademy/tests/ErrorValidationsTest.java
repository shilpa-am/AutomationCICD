package shilpaamshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shilpaamshettyacademy.TestComponents.BaseTest;
import shilpaamshettyacademy.TestComponents.Retry;
import shilpaamshettyacademy.pageobjects.CartPage;
import shilpaamshettyacademy.pageobjects.CheckoutPage;
import shilpaamshettyacademy.pageobjects.ConfirmationPage;
import shilpaamshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		landingpage.loginApplication("shilpaam55@gmail.com", "Qwerty1");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		String productname = "ZARA COAT 3";
		String country = "India";
		ProductCatalogue productCatalogue = landingpage.loginApplication("shilpaam55@gmail.com", "Qwerty1!");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("po");
		Assert.assertFalse(match);

	}
}
