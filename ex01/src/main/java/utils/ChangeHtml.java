package utils;

public class ChangeHtml {
	public static String change(String str) {
		
		String returnStr = str;
		
		 
	    returnStr = returnStr.replaceAll(">", "&gt;");
	 
	    returnStr = returnStr.replaceAll("<", "&lt;");
	    
	    returnStr = returnStr.replaceAll("\r\n", "<br>");
	    
	    returnStr = returnStr.replaceAll("\n", "<br>");
	 
	    //returnStr = returnStr.replaceAll("", "&quot;");
	    
	    returnStr = returnStr.replaceAll(" ", "&nbsp;");
	 
	    returnStr = returnStr.replaceAll("&", "&amp;");
		
		
	    
	    return returnStr;
		
	}
}
