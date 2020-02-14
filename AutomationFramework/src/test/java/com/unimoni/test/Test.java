package com.unimoni.test;

import com.unimoni.page.objects.TestPage;
import com.unimoni.setup.BaseTest;

public class Test extends BaseTest {

	@org.testng.annotations.Test
	public void name() {
		TestPage testPage = new TestPage();
		testPage.compare(5, 5);
		testPage.compare(6, 6);
		testPage.compare("HI", "HI");
	}

}
