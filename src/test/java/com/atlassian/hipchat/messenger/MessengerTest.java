package com.atlassian.hipchat.messenger;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class MessengerTest  {

	@Test
	   public void testIt() throws JSONException, MalformedURLException, UnsupportedEncodingException, IOException{

		      String chatEndPoint = "http://localhost:8080/Messenger/hipchat/";
		      System.setProperty("file.encoding", StandardCharsets.UTF_8.name());
		      
		    	  
		          String messages[] = {
		         	 "@chris you around?",
		         		 "Good morning! (megusta) (coffee)",
		         		 "Olympicsare starting soon; http://www.nbcolympics.com",
		         		 "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016",
		         	     };

		          for (int i = 0; i < messages.length; i++) {
		        	  // double encode content to avoid 400 error.
		         		String encoded=URLEncoder.encode(URLEncoder.encode(messages[i],StandardCharsets.UTF_8.name()), StandardCharsets.UTF_8.name());
		         		System.out.println(chatEndPoint+encoded);
		        	  JSONObject json=new JSONObject();
		        	  json.put("",IOUtils.toString(new URL(chatEndPoint+encoded).openStream(),StandardCharsets.UTF_8.name()));
			    	  System.out.println(json.toString());
				}
		      
		   }
	

}