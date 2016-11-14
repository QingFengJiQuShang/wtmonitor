package tcpip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import com.jrsoft.fri.dtjk.action.Gateway;


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
            // 获得输入流
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while (true)
            {
                byte[] buffer = new byte[256];
                int length = is.read(buffer);        
               // String str = new String(buffer, 0, length);
                Gateway j=new Gateway();
               j.query(buffer, os);
                System.out.println();
            }

        }
        catch (IOException e)
        {
        	e.printStackTrace();
        	run();
        } catch (Exception e) {
        	e.printStackTrace();
			
		}
    }

}
