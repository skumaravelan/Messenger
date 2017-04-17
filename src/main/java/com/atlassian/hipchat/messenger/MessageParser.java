package com.atlassian.hipchat.messenger;

public class MessageParser {

	    public MessageParser() { }
	    public Message parse(String msg) {
	        Message contents = new Message();
	    	String[] tokens =msg.split("\\s");
	    	for (String parts : tokens) {
				if (parts.startsWith("@")){
                contents.addMention(parts);
                }else if (parts.startsWith("(")){
	                contents.addEmoticon(parts);
	            }else if (parts.startsWith("http")) {
	                HyperLink link = HyperLink.getTitle(parts);
	                if (link != null) {
	                    contents.addLink(link);
	                }
	            }
	    	}

        return contents;
    }
}
