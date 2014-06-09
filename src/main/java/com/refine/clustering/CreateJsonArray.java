package com.refine.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Vector;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class CreateJsonArray extends JSONArray {
	
	public JSONArray create(TreeMap<String, TreeMap<String, Integer >> map, TreeMap<String, String> finalMappings) throws JSONException
	{
		ArrayList<OutputRow> output = new ArrayList<CreateJsonArray.OutputRow>();
		for (Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
			
			TreeMap <String, Integer> nGramvalue = new TreeMap<String, Integer>(entry.getValue());
			Integer TotalRowsAffected = 0;
			OutputRow outputRow = new OutputRow();
			
			
			String finalKey="";
			Integer count=0;
			Vector<String> tempMappings = new Vector<String> ();
			
			for (Entry<String, Integer> Inputentry : nGramvalue.entrySet()) {
				
				ClusterRow clusterRow = new ClusterRow();
				clusterRow.string = Inputentry.getKey();
				
				TotalRowsAffected += Inputentry.getValue();
				
				tempMappings.add(Inputentry.getKey());
				
				if(Inputentry.getValue() > count)
				{
					count = Inputentry.getValue();
					finalKey = Inputentry.getKey();
					
				}
				
				clusterRow.numRows = Inputentry.getValue();
				outputRow.clusters.add(clusterRow);
				
			}
			
			//entry.getValue());
			outputRow.output = finalKey;
			outputRow.totalRowsAffected =  TotalRowsAffected;
			
			for(int i = 0 ; i < tempMappings.size(); i++)
			{
				finalMappings.put(tempMappings.elementAt(i),finalKey);
			}
			
			output.add(outputRow);
		}
		
		Collections.sort(output);
		
		JSONArray jsonArray = new JSONArray();
		for(OutputRow outputRow : output) {
			JSONObject ClusterObj = new JSONObject();
			JSONArray InputEntries = new JSONArray();
			for(ClusterRow clusterRow : outputRow.clusters) {
				JSONObject InputObject  = new JSONObject( );
				InputObject.put("String", clusterRow.string);
				InputObject.put("NumRows", clusterRow.numRows);
				InputEntries.put(InputObject);
			}
			
			ClusterObj.put("Cluster", InputEntries); 	
			ClusterObj.put("TotalRowsAffected", outputRow.totalRowsAffected);
			
			ClusterObj.put("Output", outputRow.output);
			
			jsonArray.put(ClusterObj);
		}
		return jsonArray;
		
	}
	
	private class ClusterRow implements Comparable<ClusterRow>{
		private int numRows;
		private String string;
		
		public int compareTo(ClusterRow o) {
			if(numRows == o.numRows)
				return o.string.compareToIgnoreCase(string);
			return (new Integer(o.numRows)).compareTo(numRows);
		}
	}
	
	private class OutputRow implements Comparable<OutputRow> {
		private int totalRowsAffected;
		private String output;
		private ArrayList<ClusterRow> clusters = new ArrayList<CreateJsonArray.ClusterRow>();
		
		public int compareTo(OutputRow o) {
			if(totalRowsAffected == o.totalRowsAffected)
				return o.output.compareToIgnoreCase(output);
			return (new Integer(o.totalRowsAffected)).compareTo(totalRowsAffected);
		}
	}

}
