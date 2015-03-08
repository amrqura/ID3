package com.Bonn.ed.MachineLearning.Beans;

import com.Bonn.ed.MachineLearning.Beans.Attribute.AttributeType;

public class informationGainQuery {

	private double totalCount;
	public double getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalYes() {
		return totalYes;
	}
	public void setTotalYes(double totalYes) {
		this.totalYes = totalYes;
	}
	public double getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(double totalNo) {
		this.totalNo = totalNo;
	}
	private double totalYes;
	private double totalNo;
	
	
	public double getEntropy() {
		return entropy;
	}
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}
	private double entropy;
	
	private String queryString;
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public double getGain() {
		return Gain;
	}
	public void setGain(double gain) {
		Gain = gain;
	}
	private double Gain;
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	private AttributeType type;
	
	public EntireDataSet getAllPartiition() {
		return allPartiition;
	}
	public void setAllPartiition(EntireDataSet allPartiition) {
		this.allPartiition = allPartiition;
	}
	private EntireDataSet allPartiition;
	
	
	
	private EntireDataSet yesPartition;
	public EntireDataSet getYesPartition() {
		return yesPartition;
	}
	public void setYesPartition(EntireDataSet yesPartition) {
		this.yesPartition = yesPartition;
	}
	
	
	public EntireDataSet getNoPartition() {
		return noPartition;
	}
	public void setNoPartition(EntireDataSet noPartition) {
		this.noPartition = noPartition;
	}
	private EntireDataSet noPartition;
	
}
