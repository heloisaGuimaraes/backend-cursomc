package com.dotBR.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String word) {
		String[] vector = word.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vector.length; i++) {
			list.add(Integer.parseInt(vector[i]));
		}
		return list;
		// return Arrays.asList(word.split(",")).stream().map(x->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}

	public static String decodeParam(String word) {
		try {
			return URLDecoder.decode(word, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}

	}
}
