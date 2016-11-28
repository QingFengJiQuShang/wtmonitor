package tcpip;

import java.io.IOException;
import java.io.InputStream;
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
            // ���������
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while (true){
                byte[] buffer = new byte[256];
                int length = is.read(buffer);        
                if (length == -1) {
					break;
				}
                Gateway j=new Gateway();
               j.query(buffer, os);
                System.out.println();
            }
            Thread.sleep(1 * 1000);//����˯��1����

        }catch (IOException e) {
        	e.printStackTrace();
        	//run();
        } catch (Exception e) {
        	e.printStackTrace();
        	run();
		}
       
    }

}
