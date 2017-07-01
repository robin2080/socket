package com.zte.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ResponseThread implements Runnable{
	private int port;
	private int msgLen;
	public ResponseThread(int port,int msgLen) {
		this.port = port;
		this.msgLen = msgLen;
	}

	public void run() {
		ServerSocket responseServer;
		try {
			responseServer = new ServerSocket(port);
			while(true){
			    Socket socket = responseServer.accept();
			    new Thread(new Handler(socket,msgLen)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
}
