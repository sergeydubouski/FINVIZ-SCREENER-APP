package com.finviz.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UtilPageObject extends Base {

	WebElement we;
	Select dropdownList;

	// locate a drop down list
	public void find_dropdown_list(String xpath) {
		this.dropdownList = new Select(this.driver.findElement(By.xpath(xpath)));
	}

	// choose an option from a drop down list
	public void set_option_from_dropdown_list(String value) {
		this.dropdownList.selectByValue(value);
	}

	// locate a web element
	public void find_web_element(String xpath) {
		this.we = this.driver.findElement(By.xpath(xpath));
	}

	// get text from a web element
	public String get_text() {
		// String rsi = this.locate_web_element(xpath).getText();
		return this.we.getAttribute("outerText");
	}

}
