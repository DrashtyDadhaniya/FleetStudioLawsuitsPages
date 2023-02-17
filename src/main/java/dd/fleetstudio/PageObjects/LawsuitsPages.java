package dd.fleetstudio.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
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

		String plaintiffName;
		String filingdate;
		String stateOffiling;

		String defendantName;
		String website;
		String industry;
		String summary;

		public Info(String plaintiffName, String filingdate, String stateOffiling, String defendantName,
				String website, String industry, String summary) {

			this.plaintiffName = plaintiffName;
			this.filingdate = filingdate;
			this.stateOffiling = stateOffiling;

			this.defendantName = defendantName;
			this.website = website;
			this.industry = industry;
			this.summary = summary;
		}
		
		public void print()
		{
			System.out.println("Plaintiff");
			System.out.println(this.plaintiffName);
			System.out.println(this.filingdate);
			System.out.println(this.stateOffiling);
			
			System.out.println("Defendant");
			System.out.println(this.defendantName);
			System.out.println(this.website);
			System.out.println(this.industry);
			System.out.println(this.summary);
		}
	}

	@FindBy(css = "div.post-item a")
	private List<WebElement> allLinks;

	@FindBy(xpath = "//div[@class='plaintiff_content']")
	private WebElement plaintiff;

	@FindBy(xpath = "//div[@class='defendant_content']")
	private WebElement defendant;

	public ArrayList<Info> GetPageHeaderSections() {

		ArrayList<Info> al = new ArrayList<Info>();
		
		waitForListOfWebElementsToAppear(allLinks);
		int count = 0;
		
		
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

			waitForWebElementToAppear(plaintiff);
			
			// System.out.println("***********************" +
			// plaintiff.findElements(By.cssSelector("span.legal_label")));
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[1]")).getText());
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[2]")).getText());
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[3]")).getText());

			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[1]")).getText());
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[2]")).getText());
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[3]")).getText());
			System.out.println("***********************"
					+ plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[4]")).getText());
			System.out.println("**********************DONE****************");

			Info infoObj = new Info(
				plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[1]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[2]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='plaintiff_block']//li[3]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[1]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[2]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[3]")).getText(),
				plaintiff.findElement(By.xpath("//div[@class='defendant_block']//li[4]")).getText()
			);

			al.add(infoObj);
			
			driver.close();
			driver.switchTo().window(parentId);
			
			System.out.println("**************************************" + driver.getCurrentUrl() + "********");
			
			count++;
			if (count >= 2)
				break;
			System.out.println("**************************************Continuing********");
		}
		System.out.println("**************************************out of for loop********");
		return al;
	}

	public void goTo() {
		driver.get("https://www.accessibility.com/digital-lawsuits");
	}
}
