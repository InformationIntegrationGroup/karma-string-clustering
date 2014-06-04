package com.refine.clustering;

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Vector;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class CreateJsonArray extends JSONArray {
	
	public JSONArray create(TreeMap<String, TreeMap<String, Integer >> map, TreeMap<String, String> finalMappings) throws JSONException
	{
		JSONArray jsonArray = new JSONArray();
		for (Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
			
			TreeMap <String, Integer> nGramvalue = new TreeMap<String, Integer>(entry.getValue());
			Integer TotalRowsAffected = 0;
			JSONArray InputEntries = new JSONArray();
			
			JSONObject ClusterObj = new JSONObject();
			String finalKey="";
			Integer count=0;
			Vector<String> tempMappings = new Vector<String> ();
			
			for (Entry<String, Integer> Inputentry : nGramvalue.entrySet()) {
				
				JSONObject InputObject  = new JSONObject( );
				InputObject.put("String",Inputentry.getKey());
				TotalRowsAffected += Inputentry.getValue();
				
				tempMappings.add(Inputentry.getKey());
				
				if(Inputentry.getValue() > count)
				{
					count = Inputentry.getValue();
					finalKey = Inputentry.getKey();
					
				}
				
				InputObject.put("NumRows",Inputentry.getValue());
				InputEntries.put(InputObject);
				
			}
			
			//entry.getValue());
			
			JSONObject TotalRows = new JSONObject();
			TotalRows.put("TotalRowsAffected", TotalRowsAffected);
				
			
			ClusterObj.put("Cluster", InputEntries); 	
			ClusterObj.put("TotalRowsAffected", TotalRowsAffected);
			
			ClusterObj.put("Output", finalKey);
			
			
			for(int i = 0 ; i < tempMappings.size(); i++)
			{
				finalMappings.put(tempMappings.elementAt(i),finalKey);
			}
			
			jsonArray.put(ClusterObj);
			
		}
		
		return jsonArray;
		
	}

}
