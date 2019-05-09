package kr.or.ddit.util;

public class PartUtil {

	public static String getFileNameFromPart(String contentDisposition) {
		// "form-data; name=\"uploadFile\"; filename=\"joker.jpg\""
		
		String[] splits = contentDisposition.split("; ");
		
		String fileName = null;
		for(String split : splits) {
			// split[0] = form-data 
			// split[1] = name=\"uploadFile\"
			// split[2] = filename=\"joker.jpg\"
			
			if(split.startsWith("filename=")) {
				fileName = split.substring(split.indexOf("\"")+1, split.lastIndexOf("\""));
			}
		}
		return fileName;
	}
}
