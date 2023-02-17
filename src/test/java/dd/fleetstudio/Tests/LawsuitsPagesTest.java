package dd.fleetstudio.Tests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import dd.fleetstudio.PageObjects.LawsuitsPages.Info;
import dd.fleetstudio.TestComponents.BaseTest;

public class LawsuitsPagesTest extends BaseTest {

	@Test(priority = 1, enabled = true)
	public void Lawsuits() {

		ArrayList<Info> allInfo = lawsuitsPages.GetPageHeaderSections();
		
		for(Info i : allInfo)
		{
			i.print();
		}

	}

}
