package com.refine.clustering;
//These are Jersey jars

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/cluster") public class MyService{

	@POST
    @Path("/post")
    @Produces("application/json")
    @Consumes("application/json")
   
    public JSONObject post(JSONArray a) throws JSONException{
	 
		UtilClusterKeyer ret = new UtilClusterKeyer();
	    return ret.parseObj(a);   
    }  	


}