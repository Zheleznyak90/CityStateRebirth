package com.android.citystaterebirth.functions;

import java.util.Random;

public class Common_func {
	public static int randNumber(int maxValue){
		int temp;
		Random randomGen = new Random();
		temp = randomGen.nextInt(maxValue);
		return temp;
	}
}
