package com.zte.socketserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler implements Runnable {
	private Socket socket;
	private int msgLen;
	public Handler(Socket socket,int msgLen){
		this.socket = socket;
		this.msgLen = msgLen;
	}

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			byte[] body = new byte[2048];
			while(true){
				int len = in.read(body); 
			    System.out.println("--------------------------response data---------------------111------------");
				System.out.println("+++++++++++"+len);
				OutputStream out = socket.getOutputStream();
				if(len > msgLen && Server.response != null){
				    out.write(Server.response);	
				    out.flush();
				    System.out.println(Server.byte2HexStr(Server.response,true));
				}else{
					if(len<1){
						System.out.println("$$$$$$$$$$$$$"+len);
						break;
					}
					byte[] res = new byte[len];
					System.arraycopy(body, 0, res, 0, len);
				    out.write(res);	
				    out.flush();
				    System.out.println(Server.byte2HexStr(res,true));					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
