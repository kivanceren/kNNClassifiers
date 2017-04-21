package com.main.kivanc;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.classifiers.kivanc.KnnClassifier;
import com.datastructre.kivanc.Data;
import com.exceptions.kivanc.IncorrectParameterException;

public class FallDetectionKNN {

	public static void main(String[] args) {

		ReadFromFile readFromFile = new ReadFromFile();
		
		List<Data> dataSet = readFromFile.getDataFromFile(new File("dataSetShuffle.txt"), 4);
	  
	   //Collections.shuffle(dataSet);
	    
	 //readFromFile.writeData(new File("dataSetShuffle.txt"), dataSet);
	    
	    List<Data> divideDataSet, divideTestSet;
	    
	    int percent66 = dataSet.size()*66/100;
	     
	    divideDataSet = dataSet.subList(0, percent66);
	    divideTestSet = dataSet.subList(percent66, dataSet.size());
	    
	   /* double attr[] = { -0.27772522, 0.03112793, 9.476257, 9.480377};
	    Data data = new Data(attr, 0);*/
	    
	    
	    double attr[] = {-0.102, 3.912, 9.378, 10.161743551182543};
	    Data data = new Data(attr, 0);
	    
	    KnnClassifier knnClassifier = new KnnClassifier(0, 5, 2, divideDataSet, divideTestSet);
	    
	    try {
	    	
			   int classLabel = knnClassifier.kNNClassifierMethod(data);
			   data.setClassLabel(classLabel);
			   System.out.println("Class is:" + classLabel);
			   System.out.println(data.toString());
			   /*knnClassifier.createConfusionMatrix();
			   knnClassifier.printConfusionMatrix();		   
			   System.out.println(knnClassifier.accuaryOfClassifiers() );*/
			   
		} catch (IllegalStateException | IncorrectParameterException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
