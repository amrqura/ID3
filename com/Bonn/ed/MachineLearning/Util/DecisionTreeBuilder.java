package com.Bonn.ed.MachineLearning.Util;

import java.util.ArrayList;
import java.util.List;

import com.Bonn.ed.MachineLearning.Beans.*;
import com.Bonn.ed.MachineLearning.Beans.Attribute.AttributeType;
import com.Bonn.ed.MachineLearning.Beans.DecisionTree.BinTree;
import com.Bonn.ed.MachineLearning.Beans.EntireDataSet.Comarison;
public class DecisionTreeBuilder {

	private EntireDataSet fullDataSets;
	private List<Attribute> dataAttributes;
	private int nodeNum=0;
	
	

	public DecisionTreeBuilder(EntireDataSet fullDataSets) {
		super();
		this.fullDataSets = fullDataSets;
	}
	
	
	private DecisionTree TDIDT(EntireDataSet example,List<Attribute> attributes,DecisionTree T, BinTree Node)
	{
		System.out.println("-----------------------------------------------------------------------");
		if(Node!=null)
		System.out.println("calling TDIDT with Node: "+Node.getNodeNum()+", and  "+example.getEntireFileExamples().size()+" examples");
		else
		{
			System.out.println("node is null");
			return T;
		}
		System.out.println("-----------------------------------------------------------------------");
		// 3-assign Test as test for No		
		if(attributes.size()==0)
			return T;
		//2- select "Test", the best decision attribute/test for node N
		Attribute Test=getNextAttribute(example);
		if(Test==null)
			return T;
		
		// remove the the attribute
		if(Test.getAttributeType()==AttributeType.C)
			for(int i=0;i<attributes.size();i++)
			{
				if(attributes.get(i).getAttributeName().equals(Test.getAttributeName()))
					attributes.remove(i);
			
			}
		// if no more split, return
		if(Test.getInformationGain()==0)
			return T;
		
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("selecting the attribute: "+Test.getAttributeName()+", and test "+Test.getQuestion()+" with information gain:"+Test.getInformationGain());
		System.out.println("spliting at node "+Test.getAttributeName()+"with (+)"+Test.getTotalTrue()+" samples and (-)"+Test.getTotalNo()+" samples");
		System.out.println("-----------------------------------------------------------------------");
		// 3-assign Test as test for Node 
		Node.setProvidedAttribute(Test);
		// 5- for each value of Test , do
		 // for each attribute test, we have only two values , true or false
		// handle the yes case
		
		if(Test.getYesDataSet()!=null &&Test.getYesDataSet().getEntireFileExamples().size()!=0) //not leaf node
		{
			BinTree yesNode=T.addYesNode(++nodeNum, Test.getAttributeID(), "", "");
			TDIDT(Test.getYesDataSet(), attributes, T,yesNode);
			
		}
		else
		{
			Node.setLeaf(true);
			Node.setQuestOrAns("Answer:No");
			
		}
		
		// hanle no cases
		
		if(Test.getNoDataSeet()!=null && Test.getNoDataSeet().getEntireFileExamples().size()!=0) //not leaf node
		{
			BinTree noNode=T.addNoNode(++nodeNum, Test.getAttributeID(), "", "");
			TDIDT(Test.getNoDataSeet(), attributes, T,noNode);
			
		}
		else
		{
			Node.setLeaf(true);
			Node.setQuestOrAns("Answer:yes");
			
		}
		
		
		return T;
	}
	
	/*
	 * build decision tree from the set of examples
	 */
	public DecisionTree buildDecisionTree()
	{
		
		// get all avaliable attributes
		dataAttributes=getAvaliableAttributes(); // get all avaliable attributes
		fullDataSets.getEntireFileExamples().remove(0); // remove the header from the data
		//repeat until all attributes are vistied
		int nodeCount=1;
		
		// the resulttree
		DecisionTree tree=new DecisionTree();
		
		
		BinTree rootNode=tree.createRoot(++nodeNum,"",""); // now create the root and will be filled later
		// aplly the algorithm
		return TDIDT(fullDataSets, dataAttributes,tree, rootNode);
	}
	
	/*
	 * remove the attribute from the list after addition in the tree
	 * 
	 */
	private void removeAttribute(Attribute param)
	{
		for(int i=0;i<dataAttributes.size();i++)
		{
			if(dataAttributes.get(i).getAttributeName().trim().equals(param.getAttributeName().trim()))
				dataAttributes.remove(i);
			
		}
	}
	/*
	 * get remoaining attributes
	 */
	private List<String> getRemainingAttributes(List<String> usedAttributes)
	{
		List<String> tmpAttibuteList=new ArrayList<String>();
		// fill first
		for(Attribute tmpAttr:dataAttributes)
			tmpAttibuteList.add(tmpAttr.getAttributeName());
		
		for(int i=0;i<tmpAttibuteList.size();i++)
		{
			for(int j=0;j<usedAttributes.size();j++)
			if(tmpAttibuteList.get(i).trim().toUpperCase().equals(usedAttributes.get(j).trim().toUpperCase()))
			{
				tmpAttibuteList.remove(i);
			}
		}
		
		return tmpAttibuteList;
		
	}
	/*
	 * this function will compute the information gain and return the best attribute to be used based on max information gain
	 */
	private Attribute getNextAttribute(EntireDataSet currentDataSet)
	{
		Attribute result=null;
		Attribute selectedAttribute;
		double maxGain=-1;
		
		for(Attribute currentAttribue :dataAttributes )
		{
			informationGainQuery resultQuery=informationGain(currentAttribue,currentDataSet);
			
			if(resultQuery.getGain()>maxGain)
			{
				maxGain=resultQuery.getGain();
				
				currentAttribue.setQuestion(resultQuery.getQueryString());
				currentAttribue.setInformationGain(resultQuery.getGain());
				result=currentAttribue;
				
				result.setFullDataSet(resultQuery.getAllPartiition());
				result.setYesDataSet(resultQuery.getYesPartition());
				result.setNoDataSeet(resultQuery.getNoPartition());
				
				result.setTotalTrue(resultQuery.getTotalYes());
				result.setTotalNo(resultQuery.getTotalNo());
				result.setTotalExamplesNumbers(result.getTotalNo()+result.getTotalTrue());
				
				
			}
		}
		if(maxGain==0)
			return null;
		return result;
		
	}
	/*
	 * private function that will get all Avaliable Attributes in the file from the first line
	 * 
	 */
	private List<Attribute> getAvaliableAttributes()
	{
		List<Attribute> result=new ArrayList<Attribute>();
		DataSetEntry firstRow=fullDataSets.getEntireFileExamples().get(0);
		int i=0;
		for(String attributeName : firstRow.getEntry())
		{
			if(!attributeName.split(":")[1].equals("t"))
				result.add(new Attribute(attributeName,i++));
		}
		
		return result;
	}
	
	/*
	 * calculate the information gain for specific attribute
	 */
	private informationGainQuery informationGain(Attribute providedAttribute,EntireDataSet paramDataSet)
	{
		
		AttributeType type=providedAttribute.getAttributeType();
		System.out.println("calculating the information gain for attribute"+providedAttribute.getAttributeName());
		switch(type)
		{
		case C: // Categorical
			System.out.println(providedAttribute.getAttributeName()+" is categorical");
			return categoricalInformationGain(providedAttribute,paramDataSet);
			//System.out.println("Categorical");
			//break;
			
		case N:
			System.out.println(providedAttribute.getAttributeName()+" is Numerical");
			return numericalInformationGain(providedAttribute,paramDataSet);
			
			//break;
			
		case B:
			System.out.println(providedAttribute.getAttributeName()+" is binary");
			return BinaryInformationGain(providedAttribute,paramDataSet);
		}
		
		return new informationGainQuery(); // fail
		
	}
	
	/*
	 * filter powerSet
	 */
	private void filterPowerSet()
	{
		for (int i=0;i<powerSet.size();i++)
		{
			List<String> tmpList=powerSet.get(i);
			if(tmpList==null || tmpList.size()==0)
				powerSet.remove(i);
			
		}
		
	}
	/*
	 * calculate categorical infromationGain
	 * 
	 */
	private informationGainQuery categoricalInformationGain(Attribute providedAttribute,EntireDataSet paramDataSet)
	{
		informationGainQuery result=new informationGainQuery();
		double maxGain=-1.0;
		// get alla avalibale values for the attribute
		List<String> categoryList=paramDataSet.getCategoricalValues(providedAttribute);
		//List<List<String>> permuationList=generatePerm(categoryList);
		buildPowerSet(categoryList, categoryList.size());
		filterPowerSet();
		for(int i=0;i<powerSet.size();i++)
		{
			//get one subset
			List<String> currentSet=powerSet.get(i);
			
			
			// calculate the information gain for the split A < m for the current node
			informationGainQuery tmpNode=getCategoricalInformationGain(providedAttribute, paramDataSet, currentSet);
			if(tmpNode.getGain()>maxGain)
			{
				maxGain=tmpNode.getGain();
				result=tmpNode;
				
				result.setGain(maxGain);
				String query=providedAttribute.getAttributeID()+"in"+SetName(currentSet);
				
				result.setQueryString(query);
				
			}
		}
		powerSet.clear();
		result.setType(AttributeType.C);
		return result;
		
		
	}
	
	/*
	 * get information gain for one category
	 */
	private informationGainQuery getCategoricalInformationGain(Attribute providedAttribute,EntireDataSet paramDataSet,List<String> category)
	{
		//informationGainQuery result=new informationGainQuery();
		
		CountQueryResult count= paramDataSet.countEntropy();
		double entropy=entropy(count); // before split
		CountQueryResult countYes=paramDataSet.CountQuery(providedAttribute, Comarison.IN, category);
		double positiveEntropy=entropy(countYes); // split in yes
		
		CountQueryResult countNo=paramDataSet.CountQuery(providedAttribute, Comarison.NOTIN, category);
		double negativeEntropy=entropy(countNo); // split in no
		
		double informationGain=entropy-((countYes.getTotalOccurance()/count.getTotalOccurance())*positiveEntropy)
				-((countNo.getTotalOccurance()/count.getTotalOccurance())*negativeEntropy);
		
		System.out.println("first is ="+((countYes.getTotalOccurance()/count.getTotalOccurance())*positiveEntropy)+"");
		System.out.println("second is ="+((countNo.getTotalOccurance()/count.getTotalOccurance())*negativeEntropy));
		
		
		
		System.out.print("computing information gain for categorical attribut if: "+providedAttribute.getAttributeName()+"  in"+SetName(category)+":");
		System.out.print(informationGain+"\n");
		informationGainQuery result=new informationGainQuery();
		result.setTotalYes(countYes.getTotalOccurance());
		result.setTotalNo(countNo.getTotalOccurance());
		result.setTotalCount(result.getTotalNo()+result.getTotalYes());
		result.setGain(informationGain );
		result.setYesPartition(countYes.getAllPartiition());
		result.setNoPartition(countNo.getAllPartiition());
		String query="is Value of attribute A is in "+SetName(category);
		result.setQueryString(query);
		result.setType(AttributeType.C);
		
		return result;
	}
	
	/*
	 * return set description in one string
	 */
	private String SetName(List<String> param)
	{
		String result="{";
		
		for(int i=0;i<param.size();i++)
			if(i!=param.size()-1)
				result=result+param.get(i)+",";
			else
				result=result+param.get(i);
		result=result+"}";
		
		
		return result;
	}
	/*
	 * get permutation of list
	 */
	List<List<String>> powerSet=new ArrayList<List<String>>();
	
	private  void buildPowerSet(List<String> list, int count)
	{
		
	    powerSet.add(list);

	    for(int i=0; i<list.size(); i++)
	    {
	        List<String> temp = new ArrayList<String>(list);
	        temp.remove(i);
	        buildPowerSet(temp, temp.size());
	    }
	}
	
	
	private List<List<String>> generatePerm(List<String> original) {
	     if (original.size() == 0) { 
	       List<List<String>> result = new ArrayList<List<String>>();
	       result.add(new ArrayList<String>());
	       return result;
	     }
	     String firstElement = original.remove(0);
	     List<List<String>> returnValue = new ArrayList<List<String>>();
	     List<List<String>> permutations = generatePerm(original);
	     for (List<String> smallerPermutated : permutations) {
	       for (int index=0; index <= smallerPermutated.size(); index++) {
	         List<String> temp = new ArrayList<String>(smallerPermutated);
	         temp.add(index, firstElement);
	         returnValue.add(temp);
	       }
	     }
	     return returnValue;
	   }
	
	/*
	 * calculate numerical infromationGain for one numerical attribute
	 * 
	 */
	private informationGainQuery numericalInformationGain(Attribute providedAttribute,EntireDataSet paramDataSet)
	{
		
		// sort the examples covered by the current node according to the values of A
		List<Integer> sortedClasses=paramDataSet.getSortedAttributeClasses(providedAttribute);
		List<Double> sortedMeans=new ArrayList<Double>();
		double maxGain=-1;
		informationGainQuery result=new informationGainQuery();
		//forall adjacent examples with dierent classes do
		for(int i=0;i<sortedClasses.size()-1;i++)
		{
			//take the mean m of the values of A for the two examples
			double mean=(sortedClasses.get(i)+sortedClasses.get(i+1))/2.0;
			sortedMeans.add(mean);
			// calculate the information gain for the split A < m for the current node
			
			informationGainQuery tmpNode=numericalInformationGain(providedAttribute, mean,paramDataSet);
			System.out.println("computing information gain for numerical attribute with A<"+mean+":="+tmpNode.getEntropy()+"::"+tmpNode.getGain());
			if(tmpNode.getGain()>maxGain)
			{
				maxGain=tmpNode.getGain();
				result=tmpNode;
				
				result.setGain(maxGain);
				result.setQueryString(providedAttribute.getAttributeID()+"<"+mean);
			}
		}
		result.setType(AttributeType.C);
		return result;
		
	}
	/*
	 * compute the information gain for the numerical attribute with lower than specific vakue
	 * 
	 */
	private informationGainQuery numericalInformationGain(Attribute providedAttribute,double value,EntireDataSet paramDataSet)
	{
		//return fullDataSets.getNumericEntopy(providedAttribute, value);
		//before the split
		CountQueryResult count= paramDataSet.countEntropy(); //general
		double entropy=entropy(count); // before split
		
		// after the split the yes part
		//I need to know how many items satisfy the condition and want to know also how many of them are positive and how many are negative
		// so I can compute the entropy in the lower case
		CountQueryResult countYes=paramDataSet.CountQuery(providedAttribute, Comarison.LOWER, value);
		double positiveEntropy=entropy(countYes); // split in <
		
		// after the split: the no part
		CountQueryResult countNo=paramDataSet.CountQuery(providedAttribute, Comarison.BIGGER, value);
		double negativeEntropy=entropy(countNo); // split in >
		
		// original entropy - [positiveEntropy*(|SV|/|S|)] - [negativeEntropy*(|SV|/|S|)]
		double informationGain=entropy-((countYes.getTotalOccurance()/count.getTotalOccurance())*positiveEntropy)
				-((countNo.getTotalOccurance()/count.getTotalOccurance())*negativeEntropy);
		
		informationGainQuery result=new informationGainQuery();
		result.setGain(informationGain   );
		result.setTotalYes(countYes.getTotalOccurance());
		result.setTotalNo(countNo.getTotalOccurance());
		result.setTotalCount(result.getTotalNo()+result.getTotalYes());
		result.setYesPartition(countYes.getAllPartiition());
		result.setNoPartition(countNo.getAllPartiition());
		result.setQueryString("split in node "+providedAttribute.getAttributeName());
		result.setEntropy(positiveEntropy);
		return result;
	}
	
	/*
	 * calculate Binary infromation Gain
	 * 
	 */
	private informationGainQuery BinaryInformationGain(Attribute providedAttribute,EntireDataSet paramDataSet)
	{
		CountQueryResult count= paramDataSet.countEntropy();
		double entropy=entropy(count); // before split
		CountQueryResult countYes=paramDataSet.CountQuery(providedAttribute, Comarison.EQUAL, "yes");
		double positiveEntropy=entropy(countYes); // split in yes
		
		CountQueryResult countNo=paramDataSet.CountQuery(providedAttribute, Comarison.EQUAL, "no");
		double negativeEntropy=entropy(countNo); // split in no
		
		double informationGain=entropy-((countYes.getTotalOccurance()/count.getTotalOccurance())*positiveEntropy)
				-((countNo.getTotalOccurance()/count.getTotalOccurance())*negativeEntropy);
		System.out.println("computing the binary information gain for the node: "+providedAttribute.getAttributeName()+" os true, the gain="+informationGain);		
		informationGainQuery result=new informationGainQuery();
		result.setTotalYes(countYes.getTotalOccurance());
		result.setTotalNo(countNo.getTotalOccurance());
		result.setTotalCount(result.getTotalNo()+result.getTotalYes());
		
		result.setGain(informationGain );
		result.setYesPartition(countYes.getAllPartiition());
		result.setNoPartition(countNo.getAllPartiition());
		result.setQueryString("split in node "+providedAttribute.getAttributeName());
		result.setType(AttributeType.B);
		
		return result;
		
		// the basic case of the 
	}
	
	
	
	
	/*
	 * calculate the entropy for specific attribue H(SV)
	 * 
	 */
	private double entropy(CountQueryResult param)
	{
		double totalExamples = param.getTotalOccurance();
	    double positiveExamples = param.getTotalPositive();
	    double negativeExamples = param.getTotalNegative();

	    if(totalExamples==0)
	    	return 0;
	    
	    
	    return (-(positiveExamples/totalExamples)*(nlog2(positiveExamples / totalExamples))) - 
	            ((negativeExamples/totalExamples)*nlog2(negativeExamples / totalExamples));
	}
	
	/*
	 * log base 2
	 */
	private double nlog2(double value) {
	    if ( value == 0 )
	      return 0;

	    return value * Math.log(value) / Math.log(2);
	  }
}


