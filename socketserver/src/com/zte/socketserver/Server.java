package com.zte.socketserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Server {
	private static final char[] hexDigits = "0123456789abcdef".toCharArray();
	public static byte[] response;	
	public static void main(String[] args) {

            Properties prop = new Properties();
            FileInputStream input;
			try {
				input = new FileInputStream("E:/gitproject/socket/socketserver/conf/init.properties");
		        prop.load(input);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             int dataport =Integer.parseInt(((String)prop.getProperty("data_port","10110")));
            int rspport =Integer.parseInt(((String)prop.getProperty("rsp_port","10111")));
            int msgLen =Integer.parseInt(((String)prop.getProperty("ignore_mes_len","0")));

        System.out.println("init socket server at port "+dataport+" for receive data");    
		new Thread(new DataThread(dataport)).start();
        System.out.println("init socket server at port "+rspport+" for test ");    
		new Thread(new ResponseThread(rspport,msgLen)).start();
	}
	  public static String byte2HexStr(byte[] bytes, boolean printable)
	  {

		if (bytes == null) return null;
	    int j = bytes.length;

	    StringBuffer sb = new StringBuffer();

	    int k = 0;
	    for (int i = 0; i < j; ++i) {
	      if ((printable) && (k++ % 16 == 0))
	        sb.append("\r\n");
	      byte byte0 = bytes[i];
	      sb.append(hexDigits[(byte0 >>> 4 & 0xF)]).append(hexDigits[(byte0 & 0xF)]);

	      if ((printable) && (k % 8 != 0))
	        sb.append(" ");
	      if ((printable) && (k % 16 == 8)) {
	        sb.append("  ");
	      }
	    }
	    return sb.toString();
	  }
	
}
