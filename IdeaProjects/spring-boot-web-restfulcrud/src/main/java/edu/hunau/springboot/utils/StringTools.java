package edu.hunau.springboot.utils;

import java.util.Random;
import java.util.UUID;

public class StringTools {
	
	/**
	 * 生成随机6位字符
	 */
	public static String getStringRamdom(int length) {
		String val = "";
		Random rendom = new Random();
		for(int i=0; i<length; i++) {
			int temp = rendom.nextInt(2)%2 == 0 ? 65 : 97;
			val +=(char)(rendom.nextInt(26) + temp);
		}
		return val;
	}
	
	
	/**
	 * 生成UUID
	 * 在分布系统里面，不用自增做主键， 一般用UUID, 
	 * @param args
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getStringRamdom(6));
		System.out.println(getUUID());
	}

}
