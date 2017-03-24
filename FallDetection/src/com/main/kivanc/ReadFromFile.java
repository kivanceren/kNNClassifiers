package com.main.kivanc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.datastructre.kivanc.Data;

public class ReadFromFile {
	
	private List<Data> dataSet;
	private BufferedReader bufferedReader;
	
	public ReadFromFile()
	{
		dataSet = new ArrayList<>();
	}
	
	public List<Data> getDataFromFile(File textFile, int attributesNumber)
	{
		try {
			 
			bufferedReader = new BufferedReader(new FileReader(textFile));
			 for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
				    double[] attr = new double[attributesNumber];
				    int classLabel;
				    String[] parts = line.split(" ");
				    for(int i=0; i < attributesNumber; i++ )
				    	attr[i] = Double.parseDouble(parts[i]);
				    classLabel = Integer.parseInt(parts[parts.length-1]);				    		
				    dataSet.add(new Data(attr, classLabel));
    	
				}
				bufferedReader.close();	
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return this.dataSet;
	}
	
	public List<Data> getDataSet(){
		System.out.println("dsfds  "+dataSet.size());
		return (this.dataSet.isEmpty()) ? null : this.dataSet;
	}
	
}
