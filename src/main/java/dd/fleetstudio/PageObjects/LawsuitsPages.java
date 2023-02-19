package dd.fleetstudio.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LawsuitsPages extends CommonMethods {

	WebDriver driver;

	public LawsuitsPages(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "div.post-item a")
	private List<WebElement> allLinks;

	@FindBy(css = "span#hs_cos_wrapper_name")
	private WebElement pageHeader;

	@FindBy(xpath = "//div[@class='plaintiff_content']//li")
	private List<WebElement> plaintiffinfo;

	@FindBy(xpath = "//div[@class='defendant_content']//li")
	private List<WebElement> defendantinfo;

	@FindBy(css = ".next-link")
	private WebElement nextButton;

	@FindBy(css = "a#hs-eu-confirmation-button")
	private WebElement cookiesButton;
	

	public ArrayList<Info> GetPageHeaderSections() {

		ArrayList<Info> al = new ArrayList<Info>();
		waitForListOfWebElementsToAppear(allLinks);

		if (cookiesButton.isDisplayed()) {
			cookiesButton.click();
		}

		int pageCount = 2;

		while (pageCount > 0) {
			pageCount--;

			int articleCount = 2;
			for (WebElement e : allLinks) {

				// Opens each link in separate tab
				String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
				e.sendKeys(clickonlinkTab);
				waitForWebElementToAppear(e);

				// switch to all open tabs one by one
				Set<String> windowHandles = driver.getWindowHandles();
				Iterator<String> it = windowHandles.iterator();
				String parentId = it.next();
				String childId = it.next();
				driver.switchTo().window(childId);

				waitForWebElementToAppear(pageHeader);

				ArrayList<String> plaintiffArray = new ArrayList<String>();

				for (WebElement i : plaintiffinfo) {
					plaintiffArray.add(i.getText());
				}

				ArrayList<String> defendantArray = new ArrayList<String>();

				for (WebElement i : defendantinfo) {
					defendantArray.add(i.getText());
				}

				Info infoObj = new Info(pageHeader.getText(), plaintiffArray, defendantArray);

				al.add(infoObj);
				driver.close();
				driver.switchTo().window(parentId);

				articleCount--;
				if (articleCount <= 0)
					break;
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", nextButton);

			if (nextButton.isDisplayed()) {
				
				js.executeScript("arguments[0].click();", nextButton);
				
			} else {
				
				break;
			}
		}
		return al;
	}

	public void goTo() {
		driver.get("https://www.accessibility.com/digital-lawsuits");
	}
	
	
	public class Info {

		public String pageHeader;
		ArrayList<String> plaintiffArray;
		ArrayList<String> defendantArray;

		public Info(String pageHeader, ArrayList<String> plaintiffArray, ArrayList<String> defendantArray) {

			this.pageHeader = pageHeader;
			this.plaintiffArray = plaintiffArray;
			this.defendantArray = defendantArray;
		}

		public void print() {
			System.out.println(pageHeader);

			System.out.println("	Plaintiff : ");
			for (String s : plaintiffArray) {
				System.out.println("		"+s);
			}

			System.out.println("	Defendant : ");
			for (String s : defendantArray) {
				System.out.println("		"+s);
			}

			System.out.println();
		}
	}
	
}
