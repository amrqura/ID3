package com.Bonn.ed.MachineLearning.Beans;

public class CountQueryResult {
	private double totalOccurance;
	public double getTotalOccurance() {
		return totalOccurance;
	}
	public void setTotalOccurance(double totalOccurance) {
		this.totalOccurance = totalOccurance;
	}
	private double totalPositive;
	public double getTotalPositive() {
		return totalPositive;
	}
	public void setTotalPositive(double totalPositive) {
		this.totalPositive = totalPositive;
	}
	public double getTotalNegative() {
		return totalNegative;
	}
	public void setTotalNegative(double totalNegative) {
		this.totalNegative = totalNegative;
	}
	
	private double totalNegative;
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
	
	public EntireDataSet getAllPartiition() {
		return allPartiition;
	}
	public void setAllPartiition(EntireDataSet allPartiition) {
		this.allPartiition = allPartiition;
	}
	private EntireDataSet allPartiition;
	
	
}
