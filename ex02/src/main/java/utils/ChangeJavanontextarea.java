package utils;

public class ChangeJavanontextarea {
	public static String change(String str) {
		
		String returnStr = str;
		
		returnStr = returnStr.replaceAll("&amp;", "&");
		 
	    returnStr = returnStr.replaceAll("&gt;", ">");
	 
	    returnStr = returnStr.replaceAll("&lt;", "<");
	 
//	    returnStr = returnStr.replaceAll("&quot;", "");
	    
	    
	    returnStr = returnStr.replaceAll("&nbsp;"," ");
	 
	    returnStr = returnStr.replaceAll("<br>","\r\n");
	    
		
	    
	    return returnStr;
		
	}
}
