package tcpip;

public class byteUtil {
	 public  String BytesHexString(byte[] b) {   
		            String ret = "";   
		            for (int i = 0; i < b.length; i++) {   
			              String hex = Integer.toHexString(b[i] & 0xFF);   
			              if (hex.length() == 1) {   
			                hex = '0' + hex;   
			              }
//			              String str=hex;
//			              if(str.equals("b0")){
//			            	  hex="1a";
//			              }
//			              if(str.equals("1a")){
//			            	  hex="b0";
//			              }
			             ret += hex;   
			             if(hex.equals("f0")){
			            	  return ret;
			              }
		           }   
		            return ret;   
		         }   
	 /**
	   * 把16进制字符串转换成字节数组
	   * @param hexString
	   * @return byte[]
	   */
	  public static byte[] hexStringToByte(String hex) {
		   int len = (hex.length() / 2);
		   byte[] result = new byte[len];
		   char[] achar = hex.toCharArray();
		   for (int i = 0; i < len; i++) {
			   int pos = i * 2;
			   result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		   }
		   return result;
	  }
	  
	  private static int toByte(char c) {
		    byte b = (byte) "0123456789ABCDEF".indexOf(c);
		    return b;
		 }


}
