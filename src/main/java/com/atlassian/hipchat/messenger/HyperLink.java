package com.atlassian.hipchat.messenger;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HyperLink {

	
    public HyperLink(String url, String pageTitle) {
		super();
		this.url = url;
		this.pageTitle = pageTitle;
	}
    

    private String url;


    private String pageTitle;

    public static HyperLink getTitle(String url) {
        try {
            if (url.startsWith("www")) {
                url = "http://"+url;
            }
            return new HyperLink(url, HyperLink.getPageTitle(new URL(url)));
        } catch (MalformedURLException e) {
            System.err.println("URL is malformed ("+url+")");
        } catch (IOException e) {
        	System.err.println("Invalid website or Page does not esxist ("+url+")");
        }
        return null;
    }





	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String title) {
        this.pageTitle = title;
    }
    
    // get content between tags . 
	public static  String getContent(String serviceResponse, String tag) {
		String beginTag = "<" + tag + ">";
		int startIndex = serviceResponse.indexOf(beginTag);
		String endTag = "</" + tag + ">";
		int endIndex = serviceResponse.lastIndexOf(endTag);
		String tagValue = null;
		if (startIndex != -1) {
				tagValue = serviceResponse.substring(startIndex + endTag.length() - 1, endIndex);
		}
		return tagValue;
	}
    private static String getPageTitle(URL url) throws IOException {
        String serviceResponse =org.apache.commons.io.IOUtils.toString(url.openStream());        
    	return getContent(serviceResponse,"title");
    }
}    