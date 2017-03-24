package com.distances.kivanc;

import com.datastructre.kivanc.Data;
import com.datastructre.kivanc.distanceFromInstance;

public class EuclideanDistance implements Metric {

	@Override
	public distanceFromInstance getDistance(Data dataSetSample, Data instance) {
		
		int size = Data.getAttributesSize();
		
		double sum = 0;
		
		for(int i = 0; i < size; i ++){
			sum += Math.pow(dataSetSample.getAttributes(i) - instance.getAttributes(i), 2);
		}
		
		return new distanceFromInstance(Math.sqrt(sum), dataSetSample.getClassLabel()); 
	
	}

}
