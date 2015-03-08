package com.Bonn.ed.MachineLearning.Beans;

import java.io.*;


// this is class is downloaded from : https://cgi.csc.liv.ac.uk/~frans/OldLectures/COMP101/AdditionalStuff/javaDecTree.html
public class DecisionTree {



	
    public class BinTree {
    	
	
	
	private String     nodeID;
	public String getNodeID() {
		return nodeID;
	}


	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}


	public int getNodeNum() {
		return nodeNum;
	}


	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}


	private int nodeNum;
    private String  questOrAns = null;
    public String getQuestOrAns() {
		return questOrAns;
	}


	public void setQuestOrAns(String questOrAns) {
		this.questOrAns = questOrAns;
	}


	private BinTree yesBranch  = null;
    private BinTree noBranch   = null;
    public boolean isDecision() {
		return decision;
	}


	private boolean decision;
    
    public boolean isLeaf() {
		return isLeaf;
	}


	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
		
	}


	private boolean isLeaf;
    
    public Attribute getProvidedAttribute() {
		return providedAttribute;
	}


	public void setProvidedAttribute(Attribute providedAttribute) {
		this.providedAttribute = providedAttribute;
		nodeID=providedAttribute.getAttributeID();
		questOrAns=providedAttribute.getQuestion();
		if(providedAttribute.getInformationGain()==0)
			setLeaf(true);
		else
			setLeaf(false);
		
	}


	private Attribute providedAttribute;
    
	
	/* CONSTRUCTOR */
	
	public BinTree(int nodeNum,String newNodeID, String newQuestAns) {
	    nodeID     = newNodeID;
	    questOrAns = newQuestAns;
	    this.nodeNum=nodeNum;
            }
	}

    /* OTHER FIELDS */

    static BufferedReader    keyboardInput = new
                           BufferedReader(new InputStreamReader(System.in));
    BinTree rootNode = null;


    public DecisionTree() {
	}

  

    /* CREATE ROOT NODE */

    public BinTree createRoot(int nodeNum,String newNodeID, String newQuestAns) {
	rootNode = new BinTree(nodeNum,newNodeID,newQuestAns);	
	System.out.println("Created root node " + newNodeID);	
	return rootNode;
	
	}
			
    /* ADD YES NODE */

    public BinTree addYesNode(int nodeNum,String existingNodeID, String newNodeID, String newQuestAns) {
	// If no root node do nothing
	
	if (rootNode == null) {
	    System.out.println("ERROR: No root node!");
	    return null;
	    }
	
	// Search tree
	BinTree newNode=searchTreeAndAddYesNode(rootNode,nodeNum,existingNodeID,newNodeID,newQuestAns);
	if (newNode!=null) {
	    System.out.println("Added node " + newNodeID +
	    		" onto \"yes\" branch of node " + existingNodeID);
	    return newNode;
	    }
	else
		{
			System.out.println("Node " + existingNodeID + " not found");
			return null;
		}
	}

    /*
     * 
     */
    public BinTree searchNode(String existingNodeID)
    {
    	return getNode(existingNodeID,rootNode);
    	
    }
    
    /*
     * search for specific node, call it with root
     */
    private BinTree getNode(String existingNodeID,BinTree famTree)
    {
    	
    	
    	BinTree result = null;
        if (famTree == null)
            return null;
        if (famTree.nodeID.equals(existingNodeID))
            return famTree;
        if (famTree.yesBranch != null)
            result = getNode(existingNodeID,famTree.yesBranch);
        if (result == null)
            result = getNode(existingNodeID,famTree.noBranch);
        return result;
        
        
        }
    
    /* SEARCH TREE AND ADD YES NODE */    
    private BinTree searchTreeAndAddYesNode(BinTree currentNode,int nodeNum,
    		String existingNodeID, String newNodeID, String newQuestAns) {
    	if (currentNode.nodeID .equals( existingNodeID)) {
	    // Found node
	    if (currentNode.yesBranch == null) currentNode.yesBranch = new
	    		BinTree(nodeNum,newNodeID,newQuestAns);
	    else {
	        System.out.println("WARNING: Overwriting previous node " +
			"(id = " + currentNode.yesBranch.nodeID +
			") linked to yes branch of node " +
			existingNodeID);
		currentNode.yesBranch = new BinTree(nodeNum,newNodeID,newQuestAns);
		}		
    	    return(currentNode.yesBranch);
	    }
	else {
	    // Try yes branch if it exists
	    if (currentNode.yesBranch != null) { 	
	        if (searchTreeAndAddYesNode(currentNode.yesBranch,
	        		nodeNum,existingNodeID,newNodeID,newQuestAns)!=null) {    	
	            //return(true);
	        	return(currentNode.yesBranch);
		    }	
		else {
    	        // Try no branch if it exists
	    	    if (currentNode.noBranch != null) {
    	    		return(searchTreeAndAddYesNode(currentNode.noBranch,nodeNum,
				existingNodeID,newNodeID,newQuestAns));
			}
		    else return(null);	// Not found here
		    }
    		}
	    return(null);		// Not found here
	    }
   	} 	
    		
    /* ADD NO NODE */

    public BinTree addNoNode(int nodeNum,String existingNodeID, String newNodeID, String newQuestAns) {
	// If no root node do nothing
	
	if (rootNode == null) {
	    System.out.println("ERROR: No root node!");
	    return null;
	    }
	
	// Search tree
	BinTree tmpNode=searchTreeAndAddNoNode(rootNode,nodeNum,existingNodeID,newNodeID,newQuestAns);
	if (tmpNode!=null) {
	    System.out.println("Added node " + newNodeID +
	    		" onto \"no\" branch of node " + existingNodeID);
	    return tmpNode;
	    
	    }
	else System.out.println("Node " + existingNodeID + " not found");
	return null;
	}
	
    /* SEARCH TREE AND ADD NO NODE */

    private BinTree searchTreeAndAddNoNode(BinTree currentNode,int nodeNum,
    		String existingNodeID, String newNodeID, String newQuestAns) {
    	if (currentNode.nodeID .equals( existingNodeID)) {
	    // Found node
	    if (currentNode.noBranch == null) currentNode.noBranch = new
	    		BinTree(nodeNum,newNodeID,newQuestAns);
	    else {
	        System.out.println("WARNING: Overwriting previous node " +
			"(id = " + currentNode.noBranch.nodeID +
			") linked to yes branch of node " +
			existingNodeID);
		currentNode.noBranch = new BinTree(nodeNum,newNodeID,newQuestAns);
		}		
    	    //return(true);
	    return currentNode.noBranch;
	    }
	else {
	    // Try yes branch if it exists
	    if (currentNode.yesBranch != null) { 
	    	BinTree tmpNode=searchTreeAndAddNoNode(currentNode.yesBranch,nodeNum,
		        	existingNodeID,newNodeID,newQuestAns);
	        if ( tmpNode!=null) {    	
	            //return(true);
	        	return tmpNode;
		    }	
		else {
    	        // Try no branch if it exists
	    	    if (currentNode.noBranch != null) {
    	    		return(searchTreeAndAddNoNode(currentNode.noBranch,nodeNum,
				existingNodeID,newNodeID,newQuestAns));
			}
		    else return(null);	// Not found here
		    }
		 }
	    else return(null);	// Not found here
	    }
   	} 	

    

    public void queryBinTree() throws IOException {
        queryBinTree(rootNode);
        }

    private void queryBinTree(BinTree currentNode) throws IOException {

        // Test for leaf node (answer) and missing branches

        if (currentNode.yesBranch==null) {
            if (currentNode.noBranch==null) System.out.println(currentNode.questOrAns);
            else System.out.println("Error: Missing \"Yes\" branch at \"" +
            		currentNode.questOrAns + "\" question");
            return;
            }
        if (currentNode.noBranch==null) {
            System.out.println("Error: Missing \"No\" branch at \"" +
            		currentNode.questOrAns + "\" question");
            return;
            }

        // Question

        askQuestion(currentNode);
        }

    private void askQuestion(BinTree currentNode) throws IOException {
        System.out.println(currentNode.questOrAns + " (enter \"Yes\" or \"No\")");
        String answer = keyboardInput.readLine();
        if (answer.equals("Yes")) queryBinTree(currentNode.yesBranch);
        else {
            if (answer.equals("No")) queryBinTree(currentNode.noBranch);
            else {
                System.out.println("ERROR: Must answer \"Yes\" or \"No\"");
                askQuestion(currentNode);
                }
            }
        }

 

    /* OUTPUT BIN TREE */

    public void outputBinTree() {

        outputBinTree("1",rootNode);
        }

    private void outputBinTree(String tag, BinTree currentNode) {

        // Check for empty node

        if (currentNode == null) return;

        // Output

        System.out.println("[" + tag + "] nodeID = " + currentNode.nodeID +
        		", question/answer = " + currentNode.questOrAns);
        		
        // Go down yes branch

        outputBinTree(tag + ".1",currentNode.yesBranch);

        // Go down no branch

        outputBinTree(tag + ".2",currentNode.noBranch);
	}      		
    }
