package tcpip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

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
//                String str = new String(buffer, 0, length);
//                System.out.println(str);
                Gateway j=new Gateway();
                //j.query(str, os);
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
				System.out.println("√¸¡Ó¥ÌŒÛ£∫E0021103F0");
				 os.write(byteUtil.hexStringToByte("E0021103F0"));
				 SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 Gateway.write(d.format(new Date())+"\tE0021103F0");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	e.printStackTrace();
        	run();
		}
       
    }

}
