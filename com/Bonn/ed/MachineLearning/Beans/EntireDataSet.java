package com.Bonn.ed.MachineLearning.Beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EntireDataSet {

	private List<DataSetEntry> EntireFileExamples;
	
	
	public EntireDataSet(List<DataSetEntry> entireFileExamples) {
		super();
		EntireFileExamples = entireFileExamples;
	}

	public EntireDataSet()
	{
		EntireFileExamples=new ArrayList<DataSetEntry>();
	}
	
	public List<DataSetEntry> getEntireFileExamples() {
		return EntireFileExamples;
	}

	public void setEntireFileExamples(List<DataSetEntry> entireFileExamples) {
		EntireFileExamples = entireFileExamples;
	}

	/*
	 * return sorted list of attribute A classes
	 */
	public List<Integer> getSortedAttributeClasses(Attribute providedAttribute)
	{
		//get position first
		int pos=providedAttribute.getAttributePos();
		// get list of all values of that position
		List<Integer> values=new ArrayList<Integer>();
		for(DataSetEntry row : EntireFileExamples)
		{
			Integer in=Integer.parseInt(row.getEntry()[pos]);
			if(!values.contains(in))
				values.add(in);
			
		}
		Collections.sort(values);
		return values;
	}
	/*
	 * get positive value count for specific Value
	 */
	private double getPositiveValue(Attribute providedAttribute,double value)
	{
		int pos=providedAttribute.getAttributePos();
		int count=0;
		for(DataSetEntry tmpDataSetEntry:EntireFileExamples)
		{
			int storedValue=Integer.parseInt(tmpDataSetEntry.getEntry()[providedAttribute.getAttributePos()]);
			if(value<storedValue && tmpDataSetEntry.getEntry()[tmpDataSetEntry.getEntry().length-1].equals("yes"))
			{
				count++;
			}
			
		}
		
		return -1;
		
	}
	
	/*
	 * get positive value count for specific Value
	 */
	private double getNegativeValue(Attribute providedAttribute,double value)
	{
		int pos=providedAttribute.getAttributePos();
		int count=0;
		for(DataSetEntry tmpDataSetEntry:EntireFileExamples)
		{
			int storedValue=Integer.parseInt(tmpDataSetEntry.getEntry()[providedAttribute.getAttributePos()]);
			if(value<storedValue && tmpDataSetEntry.getEntry()[tmpDataSetEntry.getEntry().length-1].equals("no"))
			{
				count++;
			}
			
		}
		
		return -1;
		
	}
	
	/*
	 * count entrory	
	 */
	public CountQueryResult countEntropy()
	{
		CountQueryResult result=new CountQueryResult();
		int totalCount=0;
		int totalTrue=0;
		int totalFalse=0;
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			
			if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].trim().toUpperCase().equals("YES"))
				totalTrue++;
			else
				totalFalse++;
			
		}
		result.setTotalPositive(totalTrue);
		result.setTotalNegative(totalFalse);
		result.setTotalOccurance(EntireFileExamples.size());
		return result;
		
	}
	
	/*
	 * count for the binary cases
	 * 
	 */
	public CountQueryResult countBinary(Attribute providedAttribute)
	{
		CountQueryResult result=new CountQueryResult();
		int totalCount=0;
		int totalTrue=0;
		int totalFalse=0;
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			int comparisonPos=providedAttribute.getAttributePos();
			if(tmpEntry.getEntry()[comparisonPos].trim().toUpperCase().equals("YES"))
				totalTrue++;
			else
				totalFalse++;
			
		}
		result.setTotalPositive(totalTrue);
		result.setTotalNegative(totalFalse);
		result.setTotalOccurance(EntireFileExamples.size());
		return result;
		
	}
	
	/*
	 * count for the numerical cases
	 * 
	 */
	public CountQueryResult countNumeric(Attribute providedAttribute,double value)
	{
		CountQueryResult result=new CountQueryResult();
		int totalCount=0;
		int totalTrue=0;
		int totalFalse=0;
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			int comparisonPos=providedAttribute.getAttributePos();
			if(Double.parseDouble(tmpEntry.getEntry()[comparisonPos].trim().toUpperCase())<value)
				totalTrue++;
			else
				totalFalse++;
			
		}
		result.setTotalPositive(totalTrue);
		result.setTotalNegative(totalFalse);
		result.setTotalOccurance(EntireFileExamples.size());
		return result;
		
	}
	/*
	 * 
	 * this function will count the total static for the whole file
	 */
	public CountQueryResult CountQuery()
	{
		CountQueryResult result=new CountQueryResult();
		int totalCount=0;
		int totalTrue=0;
		int totalFalse=0;
		
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].trim().toUpperCase().equals("YES"))
				totalTrue++;
			else
				totalFalse++;
			
		}
		result.setTotalNegative(totalFalse);
		result.setTotalPositive(totalTrue);
		result.setTotalOccurance(totalFalse+totalTrue);
		return result;
	}
	/*
	 * function that count number of occurance on the data
	 * 
	 */
	public CountQueryResult CountQuery(Attribute providedAttribue,Comarison operation,Object value)
	{
		CountQueryResult result=new CountQueryResult();
		int totalCount=0;
		int totalTrue=0;
		int totalFalse=0;
		EntireDataSet tmpDataSet=null;
		EntireDataSet tmpYesDataSet=null;
		EntireDataSet tmpNoDataSet=null;
		
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			int comparisonPos=providedAttribue.getAttributePos();
			switch(operation)
			{
			case EQUAL: // binary
				if(value.toString().trim().toUpperCase().equals(tmpEntry.getEntry()[comparisonPos].trim().toUpperCase()))
				{
					if(tmpDataSet==null)
						tmpDataSet=new EntireDataSet();
					tmpDataSet.getEntireFileExamples().add(tmpEntry);
					
					totalCount++;
					if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].equals("yes"))
					{
						totalTrue++;
						if(tmpYesDataSet==null)
							tmpYesDataSet=new EntireDataSet();
						tmpYesDataSet.getEntireFileExamples().add(tmpEntry);
					}
					else
					{
						totalFalse++;
						if(tmpNoDataSet==null)
							tmpNoDataSet=new EntireDataSet();
						tmpNoDataSet.getEntireFileExamples().add(tmpEntry);
					}
				}
				break;
			case BIGGER:
				double comparionValue=Double.parseDouble(value.toString());
				if(comparionValue<Double.parseDouble(tmpEntry.getEntry()[providedAttribue.getAttributePos()]))
				{
					// maintain how many elements in the group
					// maintain how many element has + in the subset
					// maintain how many elememnt has - in the subset
					if(tmpDataSet==null)
						tmpDataSet=new EntireDataSet();
					tmpDataSet.getEntireFileExamples().add(tmpEntry);
					totalCount++;
					if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].equals("yes"))
					{
						totalTrue++;
						if(tmpYesDataSet==null)
							tmpYesDataSet=new EntireDataSet();
						tmpYesDataSet.getEntireFileExamples().add(tmpEntry);
					}
					else
					{
						totalFalse++;
						if(tmpNoDataSet==null)
							tmpNoDataSet=new EntireDataSet();
						tmpNoDataSet.getEntireFileExamples().add(tmpEntry);
					}
				}
				break;
			case LOWER:
				double tocompareonValue=Double.parseDouble(value.toString());
				if(Double.parseDouble(tmpEntry.getEntry()[providedAttribue.getAttributePos()])<tocompareonValue)
				{
					// maintain how many elements in the group
					// maintain how many element has + in the subset
					// maintain how many elememnt has - in the subset
					if(tmpDataSet==null)
						tmpDataSet=new EntireDataSet();
					tmpDataSet.getEntireFileExamples().add(tmpEntry);
					
					totalCount++;
					if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].equals("yes"))
					{
						totalTrue++;
						if(tmpYesDataSet==null)
							tmpYesDataSet=new EntireDataSet();
						tmpYesDataSet.getEntireFileExamples().add(tmpEntry);
					}
					else
					{
						totalFalse++;
						if(tmpNoDataSet==null)
							tmpNoDataSet=new EntireDataSet();
						tmpNoDataSet.getEntireFileExamples().add(tmpEntry);
					}
				}
				break;
				
				// case search in range : categorical
			case IN:
				List<String> categoryList=(List<String>)value;
				//for(String tmpVal:categoryList)
				if(categoryList.contains(tmpEntry.getEntry()[comparisonPos]))
				{
					if(tmpDataSet==null)
						tmpDataSet=new EntireDataSet();
					tmpDataSet.getEntireFileExamples().add(tmpEntry);
					
					totalCount++;
					if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].equals("yes"))
					{
						totalTrue++;
						if(tmpYesDataSet==null)
							tmpYesDataSet=new EntireDataSet();
						tmpYesDataSet.getEntireFileExamples().add(tmpEntry);
					}
					else
					{
						totalFalse++;
						if(tmpNoDataSet==null)
							tmpNoDataSet=new EntireDataSet();
						tmpNoDataSet.getEntireFileExamples().add(tmpEntry);
					}
				}
				
				
				break;
				
			case NOTIN:
				List<String> ccategoryList=(List<String>)value;
				//for(String tmpVal:categoryList)
				if(!ccategoryList.contains(tmpEntry.getEntry()[comparisonPos]))
				{
					if(tmpDataSet==null)
						tmpDataSet=new EntireDataSet();
					tmpDataSet.getEntireFileExamples().add(tmpEntry);
					
					totalCount++;
					if(tmpEntry.getEntry()[tmpEntry.getEntry().length-1].equals("yes"))
					{
						totalTrue++;
						if(tmpYesDataSet==null)
							tmpYesDataSet=new EntireDataSet();
						tmpYesDataSet.getEntireFileExamples().add(tmpEntry);
					}
					else
					{
						totalFalse++;
						if(tmpNoDataSet==null)
							tmpNoDataSet=new EntireDataSet();
						tmpNoDataSet.getEntireFileExamples().add(tmpEntry);
					}
				}
				
				
				break;
			}
		}
		
		result.setTotalNegative(totalFalse);
		result.setTotalOccurance(totalCount);
		result.setTotalPositive(totalTrue);
		result.setAllPartiition(tmpDataSet);
		result.setNoPartition(tmpNoDataSet);
		result.setYesPartition(tmpYesDataSet);
		
		return result;
		
	}
	
	/*
	 * 
	 * get alla avaliable values for one categorical attribute
	 */
	public List<String> getCategoricalValues(Attribute providedAttribute)
	{
		List<String> result=new ArrayList<String>();
		for(DataSetEntry tmpEntry: EntireFileExamples)
		{
			int comparisonPos=providedAttribute.getAttributePos();
			if(!result.contains(tmpEntry.getEntry()[comparisonPos]))
				result.add(tmpEntry.getEntry()[comparisonPos]);
			
			
		}
		
		return result;
		
	}
	
	/*
	 * enum to show type of comparison
	 * 
	 */
	public enum Comarison {
	    EQUAL, BIGGER, LOWER ,IN,NOTIN
	}
	
	
	
	
}
