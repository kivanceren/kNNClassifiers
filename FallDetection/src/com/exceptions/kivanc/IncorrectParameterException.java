package com.exceptions.kivanc;

public class IncorrectParameterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stringOne, stringTwo;
	
	public IncorrectParameterException(String s1) {
		this.stringOne = s1;
	    this.stringTwo = null;
	}
	
	public IncorrectParameterException(String s1, String s2) {
		this.stringOne = s1;
		this.stringTwo = s2;
	}
	
	@Override
	public String toString()
	{
		if(stringTwo.isEmpty()) return stringOne;
		return stringOne + " ve " + stringTwo + "arasında yanlış bir ilişki var KNN kurallarını kontrol ediniz.";
		
	}
	
	

}
