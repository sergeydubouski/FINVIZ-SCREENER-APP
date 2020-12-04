package com.finviz.app;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Report {

	private BufferedWriter bw;

	// create a .txt file to write finviz screener results
	Report(String directory,String filename) {
		File dir = new File(directory);
		if (!dir.exists())
			dir.mkdirs();
		try {
			this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(directory+"\\"+filename))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//create a folder
//	Path createFileWithDir(String directory, String filename) {
//		File dir = new File(directory);
//		if (!dir.exists())
//			dir.mkdirs();
//		return Paths.get(directory + File.separatorChar + filename);
//	}

	// write to the .txt file
	void writeToFile(String line) {
		try {
			this.bw.newLine();
			this.bw.write(line);
			//this.bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//close file stream
	void close() {
		try {
			this.bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//extract a substring from a string
	String extractSubString(String s) {
	    int startIndex = s.indexOf(':');
	    int endIndex = s.indexOf('#');
		return s.substring(startIndex + 1, endIndex);
	}
	
	/*open .csv file with excel*/
	void openCsvInExcel(String file) {
		try {
			Desktop.getDesktop().open(new File(file));
		} catch (IOException e) {
			System.out.println("Cannot open a file!");
			e.printStackTrace();
		}
	}
}
