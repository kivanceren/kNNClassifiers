package com.classifiers.kivanc;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private int [][]confusionMatrix;
	private static Map<Integer, String> classMap;
	private String lastClassfiedInstaceClass;
	private double[] accuaryOfClass;
	private double accuaryOfClassifier = -1;
	
	public void setClassLabels(String[] classNames){
		classMap = new HashMap<Integer,String>();  
		for(int i=0; i<classNames.length; i++){
			classMap.put(i, classNames[i]);
		}
	}
	
	public KnnClassifier(int cN){
		super();
		classMap = null;
		this.metric_Type = 1;
		this.paramNeighbor = 1;
		this.classNumber = cN;
		setParamater();
		this.testSet = null;
	}
	
	public KnnClassifier(List<Data> dataSet, int cN){
		super();
		classMap = null;
		this.metric_Type = 1;
		this.paramNeighbor = 1;
		this.classNumber = cN;
		setParamater();
		this.testSet = null;
		this.dataSet = dataSet;
		
	}
	
	public KnnClassifier(List<Data> dataSet, List<Data> testSet, int cN){
		super();
		classMap = null;
		this.metric_Type = 1;
		this.paramNeighbor = 1;
		this.classNumber = cN;
		setParamater();
		this.testSet = null;
		this.dataSet = dataSet;
		this.testSet = testSet;
	}
	
	public KnnClassifier(int metric_Type, int paramNeighbor, int classNumber, List<Data> dataSet){
		super();
		if(metric_Type>=0 && metric_Type<=1)
			this.metric_Type = metric_Type;
		else metric_Type=0;

		if(paramNeighbor<1) paramNeighbor=1;
		else this.paramNeighbor = paramNeighbor;

		if(classNumber<=0) throw new InvalidParameterException();
		else this.classNumber = classNumber;
		
		setParamater();
		
		this.dataSet = dataSet;
	}
	
	public KnnClassifier(int metric_Type, int paramNeighbor, int classNumber, List<Data> dataSet, List<Data> testSet) throws InvalidParameterException{
		super();
		if(metric_Type>=0 && metric_Type<=1)
			this.metric_Type = metric_Type;
		else metric_Type=0;

		if(paramNeighbor<1) paramNeighbor=1;
		else this.paramNeighbor = paramNeighbor;

		if(classNumber<=0) throw new InvalidParameterException();
		else this.classNumber = classNumber;
		
		 setParamater();
		
		this.dataSet = dataSet;
		this.testSet = testSet;
	}
	

	public int kNNClassifierMethod(Data instance) throws IllegalStateException, IncorrectParameterException
	{
		//data setin alınması ve ilgili kontrollerin yapılması ile distance tiplerinin beliritlmesi
		
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
		
		String classLabel = "";
		try{
			  lastClassfiedInstaceClass= classMap.get(classOfInstace);
		}catch (Exception e) {
			lastClassfiedInstaceClass = "" +classOfInstace;
		}
			
				
		return classOfInstace;
	}
	
	
	public String getClassName(){return lastClassfiedInstaceClass;}

	private int classify(List<distanceFromInstance> distances)
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
	
	private int returnMaxClassLabel(int []classLabel)
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
	
	public void createConfusionMatrix() throws IllegalStateException, IncorrectParameterException
	{
		if(this.testSet.isEmpty()){
				System.out.print("testSet boş ConfusionMatrix oluşturulamıyor...");
			    return;
			}
		if(confusionMatrix != null) return;
		confusionMatrix = new int[classNumber][classNumber];
		for(int i=0; i<testSet.size(); i++){
			Data testData = testSet.get(i);
			int testDataClass = testData.getClassLabel();
			int kNN_Class = this.kNNClassifierMethod(testData);
			confusionMatrix[testDataClass][kNN_Class]++;	
			if(testDataClass == kNN_Class) accuaryOfClassifier++;
		}	
		accuaryOfClassifier = (accuaryOfClassifier*100) / testSet.size();
	}
	
	public double[] accuaryOfEachClasses() throws IncorrectParameterException{
		
		if(classNumber < 0) throw new IncorrectParameterException("Sınıf sayısı geçersiz. 0'dan büyük olmalı.");
		
		if(accuaryOfClass != null) return accuaryOfClass; //zaten varsa yeniden oluşturmadan dön.
		
		accuaryOfClassifier = 0;
		int counter = 0;
		
		if (confusionMatrix != null){
			accuaryOfClass = new double[classNumber];
			double matchCount, eachCount;
			for(int i=0; i<confusionMatrix.length; i++ ){
				matchCount = 0;
				eachCount = 0;
				for(int j=0; j<confusionMatrix[i].length; j++){
					eachCount += confusionMatrix[i][j];
					counter++;
					if(i==j) {
								accuaryOfClassifier++;
								matchCount+= confusionMatrix[i][j];
							}
				}
				accuaryOfClass[i] = (matchCount*100/eachCount);
			}
			accuaryOfClassifier = (accuaryOfClassifier*100) / counter;
			return accuaryOfClass;
		}
		
		
		int[] correct = new int[classNumber];
		int[] records = new int[classNumber];
		
		accuaryOfClass = null;
		accuaryOfClass = new double [classNumber];
		
		for(int i=0; i<testSet.size(); i++){
			Data testData = testSet.get(i);
			int testDataClass = testData.getClassLabel();
			int kNN_Class = this.kNNClassifierMethod(testData);
			if(testDataClass == kNN_Class){ 
											 accuaryOfClassifier++;
											 correct[testDataClass]++;
											}
			counter++;
			records[testDataClass]++;
		}
		
		accuaryOfClassifier = (accuaryOfClassifier*100) / counter;
		
		for(int i=0; i<classNumber; i++)
			accuaryOfClass[i] = ((double)correct[i]*100)/ (double)records[i];
		
		correct = null;
		records = null;
		
		
		return accuaryOfClass;
	}
	

	
	public void printConfusionMatrix()
	{
		if ( confusionMatrix == null ){
			System.out.println("Confusion Matrix oluşturulmamış.");
			return ;
		}
		for(int i=0; i<classNumber; i++ )
		{
			for(int j=0; j<classNumber; j++){
				System.out.print(confusionMatrix[i][j] + " ");
			}
			System.out.println();
			
		}
	}
	
	public int getMetric_Type() {
		return metric_Type;
	}

	public void setMetric_Type(int metric_Type) {
		this.metric_Type = metric_Type;
	    setParamater();
	}

	public int getParamNeighbor() {
		return paramNeighbor;
		
	}

	public void setParamNeighbor(int paramNeighbor) {
		this.paramNeighbor = paramNeighbor;
		 setParamater();
	}

	public List<Data> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<Data> dataSet) {
		this.dataSet = dataSet;
		 setParamater();
	} 

	public List<Data> getTestSet() {
		return testSet;
	}

	public void setTestSet(List<Data> testSet) {
		this.testSet = testSet;
		setParamater();
		
	}	
	
	public double[] getAccuaryOfEachClasses()
	{
		return accuaryOfClass;
	}
	
	public double getAccuaryOfClassifier()
	{
		return accuaryOfClassifier;
	}
	
	public void setParamater(){ //ilgili parametreler değişirse knndeki sıfırlanması gerekenleri sıfırlar.
		this.confusionMatrix = null;
		this.accuaryOfClass = null;
		this.accuaryOfClassifier = -1;
	}

}
