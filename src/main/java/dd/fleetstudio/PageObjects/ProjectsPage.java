package dd.fleetstudio.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPage extends CommonMethods{
	
	WebDriver driver;

	public ProjectsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Automation Practise - 1")
	private WebElement projectLink;
	
	public GreenCartHomePage clickOnProject() {
		
		waitForWebElementToAppear(projectLink);
		projectLink.click();				
		GreenCartHomePage greenCartHomePage = new GreenCartHomePage(driver);
		return greenCartHomePage;

	}

}
