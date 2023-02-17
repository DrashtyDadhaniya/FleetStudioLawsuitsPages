package dd.fleetstudio.PageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GreenCartHomePage extends CommonMethods {

	WebDriver driver;

	public GreenCartHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h4.product-name")
	private List<WebElement> products;

	@FindBy(css = "img[alt='Cart']")
	private WebElement cartButton;

	@FindBy(css = "div[class='cart-preview active'] button[type='button']")
	private WebElement checkout;

	@FindBy(xpath = "//a[@class='cart-header-navlink']/following-sibling::div[@class='cart-info']//tr[1]//strong")
	private WebElement ItemCountOnCartInfo;
	
	@FindBy(xpath = "//a[@class='cart-header-navlink']/following-sibling::div[@class='cart-info']//tr[2]//strong")
	private WebElement TotalPriceOnCartInfo;

	@FindBy(css = "div[class='cart-preview active'] div div p[class='amount']")
	private List<WebElement> amountOfEachProduct;
	
	public void addProductsToCart(String[] itemsNeeded) {
		
		waitForListOfWebElementsToAppear(products);
		int j = 0;

		for (int i = 0; i < products.size(); i++) {
			String[] name = products.get(i).getText().split("-");
			String formattedName = name[0].trim();
			List itemsNeededList = Arrays.asList(itemsNeeded);

			if (itemsNeededList.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == itemsNeeded.length) {
					break;
				}
			}
		}
		System.out.println("**********************************"+ItemCountOnCartInfo.getText());
		System.out.println("**********************************"+TotalPriceOnCartInfo.getText());
	}

	public CheckOutPage ProceedToCheckout() {

		cartButton.click();
		waitForWebElementToAppear(checkout);
		checkout.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
