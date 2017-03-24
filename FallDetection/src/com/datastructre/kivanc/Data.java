package com.datastructre.kivanc;

import java.util.Arrays;

import com.classifiers.kivanc.KnnClassifier;

public class Data {
	

	private double[] attributes;
	
	private int classLabel;
	
	private static int numberOfAttributes;
	
	public Data(double[] attr, int classLabel) {
		super();
		
		attributes = new double[attr.length];
		numberOfAttributes = attr.length;
		
		for(int i=0; i< attr.length ; i++ ) attributes[i] = attr[i];
				
		this.classLabel = classLabel;
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
		return "Data [attributes=" + Arrays.toString(attributes) + ", classLabel=" + classLabel + "]";
	}

	
	

}
