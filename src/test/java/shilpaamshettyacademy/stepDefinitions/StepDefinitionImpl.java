package shilpaamshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shilpaamshettyacademy.TestComponents.BaseTest;
import shilpaamshettyacademy.pageobjects.CartPage;
import shilpaamshettyacademy.pageobjects.CheckoutPage;
import shilpaamshettyacademy.pageobjects.ConfirmationPage;
import shilpaamshettyacademy.pageobjects.LandingPage;
import shilpaamshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String name, String password) {
		productCatalogue = landingpage.loginApplication(name, password);
	}

	@When("^I add the product (.+) from card$")
	public void I_add_the_product_from_card(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(5000,5000)");
		Thread.sleep(2000);
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String msg = confirmationPage.getConfimationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" something message is displayed$")
	public void something_message_is_displayed(String abc) throws Throwable {
		Assert.assertEquals(abc, landingpage.getErrorMessage());
		driver.close();
	}

}
