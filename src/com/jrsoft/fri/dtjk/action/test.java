package com.jrsoft.fri.dtjk.action;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import tcpip.ClientInputThread;
import tcpip.ClientOutputThread;

public class test {
	public static void main(String[] args) throws UnknownHostException, IOException {
		 Socket socket = new Socket("127.0.0.1", 8081);
		 new ClientInputThread(socket).start();
	      new ClientOutputThread(socket).start();
	}

}
