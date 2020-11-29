package com.finviz.app;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.Date;

import org.testng.annotations.Test;

/**
 * class ScreenerTest
 * 
 * @author SD
 * @version 1.0
 * @since 31-oct-20
 * */
public class ScreenerTest {

	public static void main(String[] args) throws IOException {

		String[] industryArrParam = new String[] { "", "biotechnology", "gold","residentialconstruction","banksregional" ,"aerospacedefense"};// input industry parameters
		String[] rsiArrParam = new String[] { "", "ob90", "ob80", "ob70", "ob60", "os40", "os30", "os20", "os10" };

		String[] industryArrOutput = new String[] { "ALL STOCK", "BIOTECHNOLOGY", "GOLD" ,"RESIDENTIAL CONSTRUCTION","BANKS-REGIONAL","AEROSPACE&DEFENSE"};// print industry parameters
		String[] rsiArrOutput = new String[] { "Total:", "Rsi>90:", "Rsi>80:", "Rsi>70:", "Rsi>60:", "Rsi<40:",	"Rsi<30:", "Rsi<20:", "Rsi<10:" };// print rsi parameters


		Report report;// write to file methods
		String dir = "C://finviz reports";
		String fileName = "finviz_report_" + Time.GetCurrentTimeStamp().replace(":", "_").replace(".", "_") + ".txt";
		
		UtilPageObject po = new UtilPageObject();// get page object methods
		po.set_driver("chrome");
		po.go_to_url("https://finviz.com/screener.ashx?v=111&ft=4");

		report = new Report(dir, fileName);// create a new folder(if it does not exist) and new report
		report.writeToFile("FINVIZ.COM SCREENER REPORT\r" + "time: " +  Time.GetCurrentTimeStamp()); // write to the report
		
		for (int i = 0; i < industryArrParam.length; i++) {
			po.wait("//select[@id='fs_ind']");
			po.find_dropdown_list("//select[@id='fs_ind']");
			po.set_option_from_dropdown_list(industryArrParam[i]);
			report.writeToFile("\n" + industryArrOutput[i]);
			for (int j = 0; j < rsiArrParam.length; j++) {
				po.wait("//select[@id='fs_ta_rsi']");
				po.find_dropdown_list("//select[@id='fs_ta_rsi']");
				po.set_option_from_dropdown_list(rsiArrParam[j]);
				po.wait("//div[@id='screener-content']/table/tbody/tr[3]/td/table//tr/td[1]");// explicit wait for visibility of the result text
				po.find_web_element("//div[@id='screener-content']/table/tbody/tr[3]/td/table//tr/td[1]");
				String result[] = po.get_text().split(" ");
				report.writeToFile(rsiArrOutput[j] + result[1]);
			}
		}
		report.close();//close buffered reader stream
		po.driver.quit();

	Runtime.getRuntime().exec("C:\\Windows\\notepad.exe " + dir + "\\" + fileName);
		
	}
}
