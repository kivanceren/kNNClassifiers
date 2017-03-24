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
		
		List<Data> dataSet = readFromFile.getDataFromFile(new File("dataSet.txt"), 4);
		
	    
	  
	    Collections.shuffle(dataSet);
	    
	    List<Data> divideDataSet, divideTestSet;
	    
	    int percent66 = dataSet.size()*66/100;
	    
	    divideDataSet = dataSet.subList(0, percent66);
	    divideTestSet = dataSet.subList(percent66, dataSet.size());
	    
	    double attr[] = {0.186, 4.12, 8.683, 9.61267314538469};
	    Data data = new Data(attr, 0);
	    
	    /*double attr[] = {2.45,-0.703, 13.91, 14.142639251568287};
	    Data data = new Data(attr, 0);*/
	    
	    KnnClassifier knnClassifier = new KnnClassifier(0, 5, 2, divideDataSet, divideTestSet);
	    
	    try {
	    	
			   int classLabel = knnClassifier.kNNClassifierMethod(data);
			   System.out.println("Class is:" + classLabel);
			   knnClassifier.createConfusionMatrix();
			   knnClassifier.printConfusionMatrix();		   
			   System.out.println(knnClassifier.accuaryOfClassifiers() );
			   
		} catch (IllegalStateException | IncorrectParameterException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
