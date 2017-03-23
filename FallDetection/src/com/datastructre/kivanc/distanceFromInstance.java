package com.datastructre.kivanc;

public class distanceFromInstance implements Comparable<distanceFromInstance>
{
	public distanceFromInstance(double distance, int classLabel)  {
		super();
		this.distance = distance;
		this.classLabel = classLabel;
	}

	private double distance;
	private int classLabel;

	public double getDistance() {
		return distance;
	}

	public int getClassLabel() {
		return classLabel;
	}

	@Override
	public int compareTo(distanceFromInstance instance) {	
		return new Double(this.distance).compareTo(instance.distance);
	}
	
}