package com.Bonn.ed.MachineLearning;

public class testClass {

	public static void main(String [] args)
	{
		double x1=1.0/16.0;
		double x2=1.0/16.0;
		double x3=1.0/16.0;
		double x4=1.0/16.0;
		
		double x5=1.0/16.0;
		double x6=1.0/16.0;
		double x7=1.0/16.0;
		double x8=1.0/16.0;
		
		double x9=1.0/16.0;
		double x10=1.0/16.0;
		double x11=1.0/16.0;
		double x12=1.0/16.0;
		
		double x13=1.0/16.0;
		double x14=1.0/16.0;
		double x15=1.0/16.0;
		double x16=1.0/16.0;
		
		
		
		//double resulr=nlog2(x1) +nlog2(x2)+  nlog2(x3)+  nlog2(x4)  +nlog2(x5) +nlog2(x6)+  nlog2(x7)+  nlog2(x8) + nlog2(x9) +nlog2(x10)+  nlog2(x11)+  nlog2(x12) + nlog2(x13) +nlog2(x14)+  nlog2(x15)+  nlog2(x16);
		
		double resulr=nlog2((1.0/8.0));
		System.out.println(-1*resulr)  ;
	}
	
	
	/*
	 * log base 2
	 */
	private static double nlog2(double value) {
	    if ( value == 0 )
	      return 0;

	    System.out.println("the value for  "+value +"is "+value *( Math.log(value) / Math.log(2)));
	    return value * Math.log(value) / Math.log(2);
	  }
}
