package tcpip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import jiexie.jiexie;


public class ServerInputThread  extends Thread
{
    private Socket socket;

    public ServerInputThread(Socket socket)
    {
        super();
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            // ���������
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while (true)
            {
                byte[] buffer = new byte[1024];
                int length = is.read(buffer);
                
                String str = new String(buffer, 0, length);
                jiexie j=new jiexie();
                j.query(str,os);
                System.out.println(str);
                System.out.println();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}