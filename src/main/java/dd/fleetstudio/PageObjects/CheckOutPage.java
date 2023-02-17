package dd.fleetstudio.PageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends CommonMethods {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input.promoCode")
	private WebElement promoCode;
	
	@FindBy(className = "promoBtn")
	private WebElement applyButton;
	
	@FindBy(className = "promoInfo")
	private WebElement promoError;
	
	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	private WebElement placeOrder;
	
	@FindBy(xpath = "//div[@class='wrapperTwo']//div//select")
	private WebElement country;

	@FindBy(className = "chkAgree")
	private WebElement TermsConditions;
	
	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	private WebElement proceed;
	
	//@FindBy(xpath = "//span[contains(text(),'Thank you, your order has been placed successfully')]")
	@FindBy(xpath = "//span[contains(text(),'Thank you')]")
	private WebElement ThankYouMsg;
	
	public String EnterPromoCode(String promo) {

		waitForWebElementToAppear(promoCode);
		promoCode.sendKeys(promo);
		applyButton.click();
		return promoError.getText();
	}
	
	public WebElement PlaceOrderAndProceed() {

		placeOrder.click();
		waitForWebElementToAppear(country);
		country.click();
		return country;
		
	}

	public String AgrreeTermsAndConditions() {

		waitForWebElementToAppear(TermsConditions);
		TermsConditions.click();
		waitForWebElementToAppear(proceed);
		proceed.click();
		return ThankYouMsg.getText();
	}
}
