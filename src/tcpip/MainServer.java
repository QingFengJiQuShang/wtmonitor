package tcpip;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public  void TcpServer() throws Exception{
		 ServerSocket serverSocket = new ServerSocket(8081);   //����� �����˿� 8081
	      System.out.println("����tcpip------------->");
	        // ֱ�����ӽ�����֮�����Ż�����ִ��
	        while (true)
	        {
	            // һֱ���ڼ���״̬,�������Դ������û�
	            Socket socket = serverSocket.accept();
	            
	            System.out.println("Connected Successfully!");
	            // ��÷������˵����������ӿͻ��˽�����Ϣ
	               InputStream is = socket.getInputStream();
	               // �������˵����������ͻ��˷�����Ϣ
	               OutputStream os = socket.getOutputStream();

	               // �������˵����
	               os.write("���ӳɹ�".getBytes());

	            // ������д�߳�
	            new ServerInputThread(socket).start();
	            new ServerOutputThread(socket).start();

	        }
	}
}
