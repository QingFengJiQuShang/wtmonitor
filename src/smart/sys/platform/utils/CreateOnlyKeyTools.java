package smart.sys.platform.utils;

public class CreateOnlyKeyTools {
	private static UUIDHexGenerator uGenerator = UUIDHexGenerator.getInstance();
	public static  synchronized String  generator(){
		return uGenerator.generate();
	}
}
