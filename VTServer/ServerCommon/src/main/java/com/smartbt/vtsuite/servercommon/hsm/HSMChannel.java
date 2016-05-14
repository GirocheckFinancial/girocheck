package com.smartbt.vtsuite.servercommon.hsm;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import com.smartbt.vtsuite.vtcommon.Constants;

public class HSMChannel
{
	static final int DEFAULT_PORT = 1501;
	static final String DEFAULT_ADDRESS = "192.168.40.90";
	
        private static final Logger log = Logger.getLogger(HSMChannel.class);
	int port;
	String address;
	
	Socket socket = null;
	InputStream fromHSM = null;
	PrintStream toHSM = null;
	
	HSMChannel()
	{
		this.port = DEFAULT_PORT;
		this.address = DEFAULT_ADDRESS;
	}
	
	HSMChannel(String address, int port)
	{
		this.port = port;
		this.address = address;
	}
	
	public void setAddressAndPort(String address, int port)
	{
		this.port = port;
		this.address = address;
	}
	
	public byte[] exchange(byte[] message) throws Exception
	{
		int responseLength;
		byte[] buffer, sizeHeader = new byte[2];
		
		try 
		{
			toHSM.write(message);
			toHSM.flush();
			
			fromHSM.read(sizeHeader, 0, 2);
			responseLength = shortFromByteArray(sizeHeader);
			buffer = new byte[responseLength];
			
			fromHSM.read(buffer, 0, responseLength);
			/*
			System.out.print(new String(message) + "\n\n");
			System.out.print(bytesToHexString(message) + "\n\n");
			System.out.print(new String(buffer) + "\n\n");
			System.out.print(bytesToHexString(buffer) + "\n\n");
			*/
			if(buffer[6] != 48 || buffer[7] != 48)
			{
                             throw new Exception(new Exception(String.valueOf(Constants.CODE_ERROR_HSM).concat(" ").concat(VTSuiteMessages.ERROR_HSM)) + new String(buffer));
           		}
			
			return buffer;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Error in exchange " + e.getMessage());
                        throw new Exception(String.valueOf(Constants.CODE_ERROR_HSM).concat(" ").concat(VTSuiteMessages.ERROR_HSM));
		}
		
		//return null;
	}
	
	public void connect()
	{
		try
		{
			socket = new Socket(address, port);
			fromHSM = socket.getInputStream();
			toHSM = new PrintStream(socket.getOutputStream(), false, "ISO-8859-1");
		}
		catch(Exception e)
		{
			log.error(" HSM Error: " + e.getMessage());
		}
	}
	
	public void disconnect()
	{
		try 
		{
			if(toHSM != null)
				toHSM.close();
			if(fromHSM != null)
				fromHSM.close();
			if(socket != null)
				socket.close();
		} 
		catch (Exception e) 
		{
			log.error(" HSM Error: " + e.getMessage());
		}
	}
	
	private int shortFromByteArray(byte[] buffer)
	{
		int mergedValue, val1, val2;
		
		val1 = buffer[0] << 8;
		val2 = (int)((short) buffer[1] & 0x00FF);
		mergedValue = val1 + val2;

		return mergedValue;
	}
	
	public static String bytesToHexString(byte[] bytes) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
