package com.unimoni.page.objects;

import com.unimoni.setup.BaseSelenium;

public class TestPage extends BaseSelenium {

	public void compare(Object actual, Object expected) {

		validate(actual, expected);

	}

}
