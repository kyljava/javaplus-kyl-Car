package com.turing.utils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			createCode();
		}

	}

	public static String createCode() {
		String [] beforeShuffle= new String [] {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
		List<String> list=Arrays.asList(beforeShuffle);
		Collections.shuffle(list);//自动洗牌
		StringBuffer sbBuffer=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sbBuffer.append(list.get(i));
			
		}
		String afterShuffle=sbBuffer.toString();
		
		String result = afterShuffle.substring(5,9);
		System.out.println(result);
		
		return result;
		
	}
	

}
