package tcpip;

import java.net.Socket;

public class MainClient {
	public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 8081);

        new ClientInputThread(socket).start();
        new ClientOutputThread(socket).start();

    }

}
