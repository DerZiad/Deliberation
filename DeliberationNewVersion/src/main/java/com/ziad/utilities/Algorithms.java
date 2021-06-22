package com.ziad.utilities;

public class Algorithms {

	
	public static String convertChaineMajiscule(String chaine) {
		String ch1 = "";
		String ch2 = "";
		int nombre = 0;
		for(int i =0;i<chaine.length();i++) {
			if(nombre == 0) {
				ch1 = (chaine.charAt(i)+"").toUpperCase();
			}else {
				ch2 = ch2 + chaine.charAt(i);
			}
			nombre++;
		}
		return ch1 + ch2.toLowerCase();
	}
}
