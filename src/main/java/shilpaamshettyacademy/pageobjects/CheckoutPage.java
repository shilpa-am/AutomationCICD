package shilpaamshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shilpaamshetty.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		// super(driver);
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By dropdownoptions = By.cssSelector(".ta-results");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement dropdown;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submitButton;

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(dropdown, countryName).build().perform();
		waitForElementToAppear(dropdownoptions);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
	}
}
