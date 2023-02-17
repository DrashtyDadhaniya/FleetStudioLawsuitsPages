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

	public class Info {

		String pageHeader;
		String plaintiffName;
		String filingdate;
		String stateOffiling;

		String defendantName;
		String website;
		String industry;
		String summary;

		public Info(String pageHeader, String plaintiffName, String filingdate, String stateOffiling,
				String defendantName, String website, String industry, String summary) {

			this.pageHeader = pageHeader;
			this.plaintiffName = plaintiffName;
			this.filingdate = filingdate;
			this.stateOffiling = stateOffiling;

			this.defendantName = defendantName;
			this.website = website;
			this.industry = industry;
			this.summary = summary;
		}

		public void print() {
			System.out.println(pageHeader);

			System.out.println("	Plaintiff : ");
			System.out.println("		" + this.plaintiffName);
			System.out.println("		" + this.filingdate);
			System.out.println("		" + this.stateOffiling);

			System.out.println("	Defendant : ");
			System.out.println("		" + this.defendantName);
			System.out.println("		" + this.website);
			System.out.println("		" + this.industry);
			System.out.println("		" + this.summary);
			System.out.println();
		}
	}

	@FindBy(css = "div.post-item a")
	private List<WebElement> allLinks;

	@FindBy(css = "span#hs_cos_wrapper_name")
	private WebElement pageHeader;

	@FindBy(xpath = "//div[@class='plaintiff_block']//li[1]")
	private WebElement plaintiffName;

	@FindBy(xpath = "//div[@class='plaintiff_block']//li[2]")
	private WebElement filingDate;

	@FindBy(xpath = "//div[@class='plaintiff_block']//li[3]")
	private WebElement stateOffiling;

	@FindBy(xpath = "//div[@class='defendant_block']//li[1]")
	private WebElement defendantName;

	@FindBy(xpath = "//div[@class='defendant_block']//li[2]")
	private WebElement website;

	@FindBy(xpath = "//div[@class='defendant_block']//li[3]")
	private WebElement industry;

	@FindBy(xpath = "//div[@class='defendant_block']//li[4]")
	private WebElement summary;

	@FindBy(css = ".next-link")
	private WebElement nextButton;

	@FindBy(css = "a.prev-link")
	private WebElement previousButton;
	
	@FindBy(css = "a#hs-eu-confirmation-button")
	private WebElement cookiesButton;

	
	public ArrayList<Info> GetPageHeaderSections() {

		ArrayList<Info> al = new ArrayList<Info>();

		waitForListOfWebElementsToAppear(allLinks);
		
		if(cookiesButton.isDisplayed())
		{
			cookiesButton.click();
		}
		
		int pageCount = 0;

		while(pageCount < 2) {
			int articleCount = 0;
			
			pageCount++;
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
				Info infoObj = new Info(pageHeader.getText(), plaintiffName.getText(), filingDate.getText(),
						stateOffiling.getText(), defendantName.getText(), website.getText(), industry.getText(),
						summary.getText());

				al.add(infoObj);
				driver.close();
				driver.switchTo().window(parentId);

				articleCount++;
				if (articleCount >= 2)
					break;
			}
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", nextButton);
			


			if(nextButton.isDisplayed()) {
				System.out.println("********Next button to be clicked**********");
				// nextButton.click();
				js.executeScript("arguments[0].click();", nextButton);
				System.out.println("********Next button clicked**********");
			} else {
				System.out.println("********Next button not displayed**********");
				break;
			}
		}
		return al;
	}

	public void goTo() {
		driver.get("https://www.accessibility.com/digital-lawsuits");
	}
}
