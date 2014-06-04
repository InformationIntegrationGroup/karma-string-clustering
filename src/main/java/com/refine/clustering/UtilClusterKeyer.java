package com.refine.clustering;


import java.util.TreeMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class UtilClusterKeyer {
	
	public JSONObject parseObj(JSONArray jsonArr) throws JSONException {
		
		
		TreeMap<String, TreeMap<String, Integer >> FingerPrintMap = 
		        new TreeMap<String, TreeMap<String, Integer>>();
		
		TreeMap<String, TreeMap<String, Integer >> NGramMap = 
		        new TreeMap<String, TreeMap<String, Integer>>();
		
		TreeMap<String, TreeMap<String, Integer >> Metaphone3Map = 
		        new TreeMap<String, TreeMap<String, Integer>>();
		
		JSONArray OutputJsonArr = new JSONArray();
		JSONObject OutputJsonObj = new JSONObject();
		
		for(int i = 0; i < jsonArr.length(); i++ ) { 
			
			
			String InputString = jsonArr.getString(i);
			
			NGramFingerprintKeyer NGram = new NGramFingerprintKeyer ();
			//MetaphoneKeyer Metaphone = new MetaphoneKeyer();
			Metaphone3Keyer Metaphone3 = new Metaphone3Keyer();
			FingerprintKeyer Fingerprint = new FingerprintKeyer();
			
			String FingerprintVal =  Fingerprint.key(InputString, (Object[]) null);
			String NGramVal = NGram.key(InputString,(Object[]) null);
			String Metaphone3Val = Metaphone3.key(InputString,(Object[]) null);
		
			MapFill.insert (FingerPrintMap, InputString, FingerprintVal);
			MapFill.insert (NGramMap, InputString, NGramVal);
			MapFill.insert (Metaphone3Map, InputString, Metaphone3Val);
			
		}
		
		TreeMap<String, String> finalMappingsFP = new TreeMap<String, String>() ;
		TreeMap<String, String> finalMappingsNG = new TreeMap<String, String>() ;
		TreeMap<String, String> finalMappingsMP = new TreeMap<String, String>() ;
			
		JSONArray jsonArrayFingerPrint = new CreateJsonArray().create(FingerPrintMap,finalMappingsFP);
		JSONArray jsonArrayNGram = new CreateJsonArray().create(NGramMap, finalMappingsNG);
		JSONArray jsonArrayMetaphone3 = new CreateJsonArray().create(Metaphone3Map, finalMappingsMP);
		
		for(int i = 0; i < jsonArr.length(); i++ ) { 
						
			String InputString = jsonArr.getString(i);
			JSONObject obj=new JSONObject();
			obj.put("String", InputString);
			obj.put("FingerPrint", finalMappingsFP.get(InputString));
			obj.put("NGram", finalMappingsNG.get(InputString));
			obj.put("Metaphone3", finalMappingsMP.get(InputString));
			OutputJsonArr.put(obj);
		
		}
	
		OutputJsonObj.put("1. Mapping",OutputJsonArr);
		OutputJsonObj.put("2. FingerPrintClustering", jsonArrayFingerPrint);
		OutputJsonObj.put("3. NGramClustering", jsonArrayNGram);
		OutputJsonObj.put("4. Metaphone3Clustering", jsonArrayMetaphone3);
		
		return OutputJsonObj;	
	}

}
