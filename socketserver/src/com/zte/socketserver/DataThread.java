package com.zte.socketserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class DataThread implements Runnable{
	private int port;
	public DataThread(int port){
		this.port = port;
	}
	public void run() {
		try {
			ServerSocket dataServer = new ServerSocket(port);
			while(true){
			    Socket socket = dataServer.accept();
			    InputStream input= socket.getInputStream();
			    Server.response = transInputstream(input);	
			    System.out.println("---------------------set response------------11666-----------------");
			    System.out.println(Server.byte2HexStr(Server.response,true));
				OutputStream out = socket.getOutputStream();
				out.write(Server.response);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	  private static byte[] transInputstream(InputStream input)throws Exception
	    {
	        int len = 9024;
	        byte[] byt= new byte[len];
	        int b=0;      
	        int offset = 0;
	        int totalLen = 0;
	        b = input.read(byt,offset,len);
	        while( b != -1)
	        {
	        	totalLen+=b;
	        	if(b<len){
	        		break;
	        	}
	        	offset+=b;
	            b = input.read(byt,offset,len);  
	        }
		  	ByteBuffer bf =ByteBuffer.allocate(totalLen);
		  	bf.put(byt,0,totalLen);
	        return bf.array();
	    }

}
