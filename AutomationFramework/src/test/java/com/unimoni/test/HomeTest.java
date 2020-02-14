package com.unimoni.test;

import com.unimoni.page.objects.HomePage;
import com.unimoni.page.objects.TestPage;
import com.unimoni.setup.BaseTest;

public class HomeTest extends BaseTest {

	@org.testng.annotations.Test
	public void name() {
		HomePage homePage = new HomePage();

		homePage.clickOnWhoWeAreLink();

	}

}
