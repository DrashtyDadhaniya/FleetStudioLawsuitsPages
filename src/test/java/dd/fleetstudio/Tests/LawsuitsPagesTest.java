package dd.fleetstudio.Tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import dd.fleetstudio.PageObjects.LawsuitsPages.Info;
import dd.fleetstudio.TestComponents.BaseTest;

public class LawsuitsPagesTest extends BaseTest {

	@Test(priority = 1, enabled = true)
	public void Lawsuits() throws IOException {

		ArrayList<Info> allInfo = lawsuitsPages.GetPageHeaderSections();
		for(Info i : allInfo)
		{
			i.print();	
		}
		
		ArrayList<String> expectedData = readExpectedDataFile();
		for (int i = 0; i < allInfo.size(); i++) {
			Assert.assertEquals(allInfo.get(i).pageHeader, expectedData.get(i));
		}

	}

}
