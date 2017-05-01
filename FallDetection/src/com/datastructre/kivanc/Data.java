package com.datastructre.kivanc;

public class Data{
	

	private double[] attributes;
	
	private int classLabel;
	
	private static int numberOfAttributes;
	
	private String dataString="";
	
	public Data(double[] attr, int classLabel) {
		super();
		
		attributes = new double[attr.length];
		numberOfAttributes = attr.length;
		
		for(int i=0; i< attr.length ; i++ ) {
			attributes[i] = attr[i];
			dataString += attr[i] + " ";
		}
				
		this.classLabel = classLabel;
	}
	
	public Data(String dataString){
		String[] stringArray = dataString.split(" ");
		attributes = new double[stringArray.length];
		for(int i=0 ; i < attributes.length ; i++ ){
			attributes[i] = Double.parseDouble(stringArray[i]);
			dataString += attributes[i] + " ";
		}
		
		
		
		this.classLabel = 0;
	}
	
	public static int getAttributesSize(){return numberOfAttributes;}
	
	public double getAttributes(int axis) throws IndexOutOfBoundsException{
		return attributes[axis];
		}

	public int getClassLabel()	{ return classLabel; }
	
	public void setClassLabel(int classLabel)
	{
		this.classLabel = classLabel;
	}

	@Override
	public String toString() {
		
		return dataString + classLabel;
	}

	
	

}
