package com.Bonn.ed.MachineLearning;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Bonn.ed.MachineLearning.Beans.DataSetEntry;
import com.Bonn.ed.MachineLearning.Beans.DecisionTree;
import com.Bonn.ed.MachineLearning.Beans.EntireDataSet;
import com.Bonn.ed.MachineLearning.Reader.DataReader;
import com.Bonn.ed.MachineLearning.Util.DecisionTreeBuilder;
import com.sun.org.apache.xerces.internal.dom.EntityReferenceImpl;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 1)
	    {
	      System.err.println("You must call MainClass with one parameter ") ;
	      System.exit(1);
	    }
		
		DataReader reader=new DataReader(args[0]);
		EntireDataSet dataSets=reader.readData();
		// split the samples into two sets
		int TrainingListSize=(2*dataSets.getEntireFileExamples().size())/3;
		int testExamplesSize=dataSets.getEntireFileExamples().size()/3;
		List<Integer> trainingSetIndecies=new ArrayList<Integer>();
		int currentIndex=1;
		trainingSetIndecies.add(0); // the attributes
		while(currentIndex<TrainingListSize)
		{
			Random rn = new Random();
			int answer = rn.nextInt(TrainingListSize) + 0;
			if(!trainingSetIndecies.contains(answer))
			{
				trainingSetIndecies.add(answer);
				currentIndex++;
				
			}
		}
		
		EntireDataSet trainingExample=new EntireDataSet();
		EntireDataSet testExample=new EntireDataSet();
		// fill trainig Example
		for(int i=0;i<dataSets.getEntireFileExamples().size();i++)
		{
			if(trainingSetIndecies.contains(i))
				trainingExample.getEntireFileExamples().add(dataSets.getEntireFileExamples().get(i));
			else
				testExample.getEntireFileExamples().add(dataSets.getEntireFileExamples().get(i));
		}
		
		
		//EntireDataSet trainingExample=new EntireDataSet(dataSets.getEntireFileExamples().subList(0, TrainingListSize));
		//EntireDataSet testExample=new EntireDataSet(dataSets.getEntireFileExamples().subList(TrainingListSize, dataSets.getEntireFileExamples().size()));
		DecisionTreeBuilder builder=new DecisionTreeBuilder(dataSets);
		DecisionTree tree=builder.buildDecisionTree();
		
		System.out.println("-----------------------------------------------printing the tree -------------");
		tree.outputBinTree();
		
		
	}

}
