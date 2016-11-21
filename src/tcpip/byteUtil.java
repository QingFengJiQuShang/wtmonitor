package tcpip;

public class byteUtil {
	 public  String BytesHexString(byte[] b) {   
		            String ret = "";   
		            for (int i = 0; i < b.length; i++) {   
			              String hex = Integer.toHexString(b[i] & 0xFF);   
			              if (hex.length() == 1) {   
			                hex = '0' + hex;   
			              }
			             
			             ret += hex;   
			             if(hex.equals("f0")){
			            	  return ret;
			              }
		           }   
		            return ret;   
		         }   
	
}
