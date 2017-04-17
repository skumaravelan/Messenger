package com.atlassian.hipchat.messenger;
 
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Senthil
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
@Path("/hipchat")
public class MessengerService {
 
    // 'custom' emoticons which are alphanumeric strings
    final int  EMOT_ICON_LENGTH=15;
	  @Path("{message}")
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getHipChatFormatMessage(@PathParam("message") String inputMsg) throws JSONException {
		  
		  JSONObject jsonObject = new JSONObject();
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  String prettyJson="";
		  String result = "";
		  String decodeMsg="";
		  if(null!=inputMsg && inputMsg.length() >2){
			  try {
				  decodeMsg=URLDecoder.decode(inputMsg,StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        MessageParser messageParser = new MessageParser();
	        jsonObject.put("Return:",  messageParser.parse(decodeMsg)); 
	         prettyJson = gson.toJson(jsonObject.toString());
		 result = String.format("Output:\n%s", prettyJson);
		return Response.status(200).entity(result).build();
	  }else{
	        jsonObject.put("Return:",  "Invalid Message"); 
		  prettyJson = gson.toJson(jsonObject.toString());
			 result = String.format("Output:\n%s", prettyJson);
			return Response.status(200).entity(result).build();

	  }
	}

}