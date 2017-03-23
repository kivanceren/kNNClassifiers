package com.datastructre.kivanc;

public class Data {
	

	private double[] attributes;
	
	private int classLabel;
	
	public Data(double[] attr, int classLabel) {
		super();
		
		attributes = new double[attr.length];
		
		for(int i=0; i< attr.length ; i++ ) attributes[i] = attr[i];
				
		this.classLabel = classLabel;
	}
	
	public int getAttributesSize(){return attributes.length;}
	
	public double getAttributes(int axis) throws IndexOutOfBoundsException{
		return attributes[axis];
		}

	public int getClassLabel()	{ return classLabel; }

}
