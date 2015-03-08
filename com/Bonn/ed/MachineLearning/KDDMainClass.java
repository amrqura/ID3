package com.Bonn.ed.MachineLearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Bonn.ed.MachineLearning.Beans.EntireDataSet;
import com.Bonn.ed.MachineLearning.Reader.DataReader;
import com.Bonn.ed.MachineLearning.Util
public class KDDMainClass {

	
	public static void main(String [] args)
	{
		if (args.length != 1)
	    {
	      System.err.println("You must call MainClass with one parameter ") ;
	      System.exit(1);
	    }
		
		DataReader reader=new DataReader(args[0]);
		EntireDataSet dataSets=reader.readData();
		KDDNearsetNeigborFinder nearestPointsFinder=new KDDNearsetNeigborFinder();
		
		// split the samples into two sets
		List<Integer> randomIndicies=new ArrayList<Integer>();
				
		for(int i=0;i<5;i++)
		{
			Random rn = new Random();
			int randomIndex = rn.nextInt(dataSets.getEntireFileExamples().size()) + 0;
			if(!randomIndicies.contains(randomIndex))
			{
				randomIndicies.add(randomIndex);
				
			}
		}
				
				
				
	}
}
