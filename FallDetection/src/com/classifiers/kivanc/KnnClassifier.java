package com.classifiers.kivanc;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.datastructre.kivanc.Data;
import com.datastructre.kivanc.distanceFromInstance;
import com.distances.kivanc.CosineSimilarity;
import com.distances.kivanc.EuclideanDistance;
import com.distances.kivanc.Metric;
import com.exceptions.kivanc.IncorrectParameterException;

public class KnnClassifier {
	
	
	private int metric_Type, paramNeighbor; //@metric_Type distanceFonksiyonun tipi, @paramNeighbor kNN'deki K.
	private List<Data> dataSet;
	private List<Data> testSet;
	private Metric distanceMethod;
	private int classNumber;
	
	public KnnClassifier(){
		super();
		this.metric_Type = 1;
		this.paramNeighbor = 1;
	}
	
	public void setClassifierParameters(int metric_Type, int paramNeighbor, int classNumber) throws InvalidParameterException
	{
		
		if(metric_Type>=0 && metric_Type<=1)
								this.metric_Type = metric_Type;
		else metric_Type=0;
		
		if(paramNeighbor<1) paramNeighbor=1;
		else this.paramNeighbor = paramNeighbor;
		
		if(classNumber<=0) throw new InvalidParameterException();
		else this.classNumber = classNumber;
	}
	
	public int kNNClassifierMethod(List<Data> dataSet, Data instance) throws IllegalStateException, IncorrectParameterException
	{
		//data setin alınması ve ilgili kontrollerin yapılması ile distance tiplerinin beliritlmesi
		this.dataSet = dataSet;
		if(dataSet.isEmpty()) throw new IllegalStateException();
		if(instance == null) throw new IllegalStateException();
		if(this.dataSet.size() < paramNeighbor) throw new IncorrectParameterException("dataSet Boyutu","En yakın komşu sayısı");
		
		//fonksiyon seçimi
		if(metric_Type ==  0 )  distanceMethod = new EuclideanDistance();
		else if(metric_Type == 1) distanceMethod = new CosineSimilarity();
		
		List<distanceFromInstance> distances = new ArrayList<>(); //instance değişkenine olan dataSetteki öğlerin uzaklıkları.
		
		for(Data data : dataSet){//tüm data setin sınıflandırılacak instance'a göre uzaklıklarının alınması. 
				distances.add(distanceMethod.getDistance(data, instance));	
		}
		
		int classOfInstace = classify(distances);//sınıflandırma
		
		return classOfInstace;
	}
	
	
	public int classify(List<distanceFromInstance> distances)
	{
		Collections.sort(distances);
		
		//sıralanmış distancelardan ilk K adedinin alınması. 
		List<distanceFromInstance> neighborDistance = new ArrayList<>(distances.subList(0, paramNeighbor));
		
		int[] classLabel = new int[classNumber];
		
		for(distanceFromInstance nD :  neighborDistance) //knndeki en yakın komşuların sınıflarının sayılması.
													classLabel[nD.getClassLabel()]++;
		
		neighborDistance = null;
		
		
		return returnMaxClassLabel(classLabel);
	}
	
	public int returnMaxClassLabel(int []classLabel)
	{
		int largest = classLabel[0], index = 0;
		for (int i = 1; i < classLabel.length; i++) {
		  if ( classLabel[i] >= largest ) {
		      largest = classLabel[i];
		      index = i;
		   }
		}
		
		return index;		
	}

	public int getMetric_Type() {
		return metric_Type;
	}

	public void setMetric_Type(int metric_Type) {
		this.metric_Type = metric_Type;
	}

	public int getParamNeighbor() {
		return paramNeighbor;
	}

	public void setParamNeighbor(int paramNeighbor) {
		this.paramNeighbor = paramNeighbor;
	}

	public List<Data> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<Data> dataSet) {
		this.dataSet = dataSet;
	}

	public List<Data> getTestSet() {
		return testSet;
	}

	public void setTestSet(List<Data> testSet) {
		this.testSet = testSet;
	}	

}
