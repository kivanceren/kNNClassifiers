package com.distances.kivanc;

import com.datastructre.kivanc.Data;
import com.datastructre.kivanc.distanceFromInstance;

public class CosineSimilarity implements Metric {

	@Override
	public distanceFromInstance getDistance(Data dataSetSample, Data instance) {
		
		int size = Data.getAttributesSize();
		
		double sum=0, sum1=0, sum2 = 0;
		
		for(int i = 0; i < size; i ++){
			sum += dataSetSample.getAttributes(i)*instance.getAttributes(i);
			sum1 += Math.pow(dataSetSample.getAttributes(i),2);
			sum2 += Math.pow(dataSetSample.getAttributes(i), 2);
		}
		sum1 = Math.sqrt(sum1);
		sum2 = Math.sqrt(sum2);
		double cosineSimilarity = sum/(sum1*sum2);
		
		return new distanceFromInstance(cosineSimilarity, dataSetSample.getClassLabel()); 
	
	}


}
