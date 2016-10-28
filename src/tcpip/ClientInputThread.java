package tcpip;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientInputThread extends Thread
{
    private Socket socket;

    public ClientInputThread(Socket socket)
    {
        super();
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            // 获得输入流
            InputStream is = socket.getInputStream();

            while (true)
            {
                byte[] buffer = new byte[256];

                int length = is.read(buffer);

                String str = new String(buffer, 0, length);

                System.out.println(str);

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
