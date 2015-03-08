package com.Bonn.ed.MachineLearning.Beans;

import java.util.List;

public class Attribute {

	private boolean leaf;

	private String attributeName;
	
	private String attributeID;
	
	private int AttributePos;
	
	private String Question;
	
	private double informationGain;
	
	
	public boolean isDecision() {
		return decision;
	}


	public void setDecision(boolean decision) {
		this.decision = decision;
	}

	private boolean decision; // in case that information gain =0
	
	public double getTotalExamplesNumbers() {
		return totalExamplesNumbers;
	}


	public void setTotalExamplesNumbers(double totalExamplesNumbers) {
		this.totalExamplesNumbers = totalExamplesNumbers;
	}

	private double totalExamplesNumbers;
	
	

	private EntireDataSet attributePositiveExamples;
	
	public EntireDataSet getAttributePositiveExamples() {
		return attributePositiveExamples;
	}


	public void setAttributePositiveExamples(EntireDataSet attributePositiveExamples) {
		this.attributePositiveExamples = attributePositiveExamples;
	}


	public EntireDataSet getAttributeNegativeExamples() {
		return attributeNegativeExamples;
	}


	public void setAttributeNegativeExamples(EntireDataSet attributeNegativeExamples) {
		this.attributeNegativeExamples = attributeNegativeExamples;
	}

	private EntireDataSet attributeNegativeExamples;
	
	
	
	


	public double getTotalTrue() {
		return totalTrue;
	}


	public void setTotalTrue(double totalTrue) {
		this.totalTrue = totalTrue;
	}


	public double getTotalNo() {
		return totalNo;
	}


	public void setTotalNo(double totalNo) {
		this.totalNo = totalNo;
	}

	private double totalTrue;
	
	private double totalNo;
	
	
	public double getInformationGain() {
		return informationGain;
	}


	public void setInformationGain(double informationGain) {
		this.informationGain = informationGain;
	}


	public String getQuestion() {
		return Question;
	}


	public void setQuestion(String question) {
		Question = question;
	}


	public int getAttributePos() {
		return AttributePos;
	}


	public String getAttributeName() {
		return attributeName;
	}


	public String getAttributeID() {
		return attributeID;
	}

	public AttributeType getAttributeType() {
		return AttributeType.valueOf(attributeType.toUpperCase());
	}

	private String attributeType;
		
			
	public Attribute(String attributeName,int AttributePos) {
		super();
		this.attributeName = attributeName;
		String [] nameContent=this.attributeName.split(":");
		attributeID=nameContent[0];
		attributeType=nameContent[1];
		this.AttributePos=AttributePos;
	}

	
	public enum AttributeType {

	    N,
	    C,
	    B	  }
	
	
	private EntireDataSet fullDataSet;
	public EntireDataSet getFullDataSet() {
		return fullDataSet;
	}


	public void setFullDataSet(EntireDataSet fullDataSet) {
		this.fullDataSet = fullDataSet;
	}


	public EntireDataSet getYesDataSet() {
		return yesDataSet;
	}


	public void setYesDataSet(EntireDataSet yesDataSet) {
		this.yesDataSet = yesDataSet;
	}


	public EntireDataSet getNoDataSeet() {
		return noDataSeet;
	}


	public void setNoDataSeet(EntireDataSet noDataSeet) {
		this.noDataSeet = noDataSeet;
	}

	private EntireDataSet yesDataSet;
	private EntireDataSet noDataSeet;
	
	
	
	
	
	
	
}
