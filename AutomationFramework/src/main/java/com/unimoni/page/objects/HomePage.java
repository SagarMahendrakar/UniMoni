package com.unimoni.page.objects;

import com.unimoni.setup.BaseSelenium;

public class HomePage extends BaseSelenium {

	public String aboutus = "//*[@id=\"aboutus-top-home\"]";

	public String whoWeare = "//*[@id=\"whoweare-top-home\"]";

	public void clickOnWhoWeAreLink() {

		mouseHover(aboutus);

		click(whoWeare);

	}

}
