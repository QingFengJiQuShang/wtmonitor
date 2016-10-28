package tcpip;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public  void TcpServer() throws Exception{
		 ServerSocket serverSocket = new ServerSocket(8081);   //服务端 监听端口 8081
	      System.out.println("启动tcpip------------->");
	        // 直到连接建立好之后代码才会往下执行
	        while (true)
	        {
	            // 一直处于监听状态,这样可以处理多个用户
	            Socket socket = serverSocket.accept();
	            
	            System.out.println("Connected Successfully!");
	            // 获得服务器端的输入流，从客户端接收信息
	               InputStream is = socket.getInputStream();
	               // 服务器端的输出流，向客户端发送信息
	               OutputStream os = socket.getOutputStream();

	               // 服务器端的输出
	               os.write("连接成功".getBytes());

	            // 启动读写线程
	            new ServerInputThread(socket).start();
	            new ServerOutputThread(socket).start();

	        }
	}
}
