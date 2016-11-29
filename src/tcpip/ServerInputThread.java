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
            // ªÒµ√ ‰»Î¡˜
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
            Thread.sleep(1 * 1000);//≥Ã–ÚÀØ√ﬂ1√Î÷”

        }catch (IOException e) {
        	e.printStackTrace();
        	//run();
        } catch (Exception e) {
        	 OutputStream os;
			try {
				os = socket.getOutputStream();
				System.out.println("√¸¡Ó¥ÌŒÛ£∫e0021102f0");
				 os.write("e0021102f0".getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	e.printStackTrace();
        	run();
		}
       
    }

}
