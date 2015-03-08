package com.Bonn.ed.MachineLearning.Reader;
import com.Bonn.ed.MachineLearning.Beans.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
	private String FilePath;

	public DataReader(String filePath) {
		super();
		FilePath = filePath;
	}
	
	public EntireDataSet readData()
	{
		
		EntireDataSet result=new EntireDataSet();
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(FilePath));
			while ((line = br.readLine()) != null) {
			        // use comma as separator
				String[] Content = line.split(cvsSplitBy);
				result.getEntireFileExamples().add(new DataSetEntry(Content));
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		return result;
		
	}
	
	
}
