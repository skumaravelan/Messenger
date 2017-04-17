package com.atlassian.hipchat.messenger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class Message {

    
    private List<String> mentions;

    
    private List<String> emoticons;

    
    private List<HyperLink> links;


    public Message() { }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public void addMention(String mention) {
        if (mentions == null) {
            mentions = new ArrayList<String>();
        }
        //TODO add sanitation logic to remove number and other special characters
        mentions.add(mention.replace("@", ""));
    }

    public List<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        this.emoticons = emoticons;
    }

    public void addEmoticon(String emoticon) {
    	String sanitizeEmoticon="";
        if (emoticons == null) {
            emoticons = new ArrayList<String>();
        }
        if (null!=emoticon){
        	sanitizeEmoticon=emoticon.replaceAll("[\\(\\)]", "");
	        if(!StringUtils.isEmpty(sanitizeEmoticon.trim()) && StringUtils.isAlphanumeric(sanitizeEmoticon) &&sanitizeEmoticon.length()<=MessageParserConfig.EMOT_ICON_LENGTH){
	        	emoticons.add(sanitizeEmoticon);
	        }else{
	        	//log and throw an error message
	        	System.err.println(String.format("Invalid emoticon:\n%s", emoticon));
	        }
        }

    }

    public List<HyperLink> getLinks() {
        return links;
    }

    public void setLinks(List<HyperLink> links) {
        this.links = links;
    }

    public void addLink(HyperLink link) {
        if (links == null) {
            links = new ArrayList<HyperLink>();
        }
        links.add(link);
    }
    
	@Override
	public String toString() {
		
		return  new Gson().toJson(this);

	}

}