package com.distances.kivanc;

import com.datastructre.kivanc.Data;
import com.datastructre.kivanc.distanceFromInstance;

/**************************************
 *Distance hesaplayan sınıflar için ortak bir arayüz oluşturmak için yaratıldı.
 *KnnClassifier sınıfında metric_Type'a göre uygun distanceFonksiyonunun kullanılması amacıyla yazılmıştır.
 *KnnClassifier içinde ;
 *		if(metric_Type ==  0 )  distanceMethod = new EuclideanDistance();
		else if(metric_Type == 1) distanceMethod = new CosineSimilarity();
 * kodunda önemi anlaşılır.
 * @author kivanceren
 **************************************/

public interface Metric { 
	public distanceFromInstance getDistance(Data dataSetSample, Data instance);
}
