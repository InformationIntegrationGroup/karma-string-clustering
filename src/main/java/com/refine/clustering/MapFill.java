package com.refine.clustering;

import java.util.TreeMap;

public class MapFill {

	public static void insert(
			TreeMap<String, TreeMap<String, Integer>> Map, String InputString, String key) {
			
		TreeMap<String, Integer> values = Map.get( key );
		
		if( null == values ) {
			values = new TreeMap<String, Integer>();
		    Map.put( key, values );
		}
		
		Integer numOfSameInput;
		
		if( values.get(InputString) != null)
			numOfSameInput = values.get(InputString) + 1;
		else
			numOfSameInput = 1;
		
		values.put(InputString, numOfSameInput);
		Map.put(key, values);
		
	}

}
