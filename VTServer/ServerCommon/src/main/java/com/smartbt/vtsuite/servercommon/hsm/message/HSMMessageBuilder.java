package com.smartbt.vtsuite.servercommon.hsm.message;

import com.smartbt.vtsuite.servercommon.hsm.message.parameter.Header;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.Command;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.HSMParameter;
import java.util.Arrays;


public abstract class HSMMessageBuilder
{
	public static final int MAX_MESSAGE_SIZE = 5000;
	public static final int MAX_PARAMETER_SIZE = 100;
	public static final int MESSAGE_SIZE_OFFSET = 2;

	protected static final String KEY_BLOCK_DELIMITER = "#";
	protected static final String VARIANT_DELIMITER = "&";
	protected static final String KEY_BLOCK_VERSION_NUMBER = "01";
	protected static final String VARIANT_VERSION_NUMBER = "01";
	protected static final String OPTIONAL_BLOCKS = "00";
	protected static final String RSA_ALGORITHM_IDENTIFIER = "01";
	protected static final String PKCS1_PAD_MODE_IDENTIFIER = "01";
	
	private int messageLength, parameterCount;
	private byte[] message;

	public Header header;
	Command command;
	HSMParameter[] hsmParameters;
	
	HSMMessageBuilder()
	{
		messageLength = MESSAGE_SIZE_OFFSET;
		parameterCount = 0;
		
		command = new Command();
		header = new Header();
		message = new byte[MAX_MESSAGE_SIZE];
		hsmParameters = new HSMParameter[MAX_PARAMETER_SIZE];
		
		addParameter(header);
		addParameter(command);
	}
	
	protected void addParameter(HSMParameter parameter)
	{
		hsmParameters[parameterCount] = parameter;
		parameterCount++;
	}
	
	public byte[] exportMessage()
	{
		byte[] exportedMessage;
		
		writeParameters();
		prependMessageSize();
		
		exportedMessage = Arrays.copyOf(message, messageLength);
		
		messageLength = MESSAGE_SIZE_OFFSET;
		
		return exportedMessage;
	}
	
	void prependMessageSize()
	{
		int tailLength = messageLength - MESSAGE_SIZE_OFFSET;
		
		message[0] = (byte) ((short) tailLength >> 8);
		message[1] = (byte) tailLength;
	}
	
	private void writeParameters()
	{
		for(int i = 0; i < parameterCount; i++)
		{
			if(hsmParameters[i].isRaw)
				appendRaw(hsmParameters[i].getRawParameter());
			else
				appendString(hsmParameters[i].getStringParameter());
		}
	}
	
	private void appendString(String parameter)
	{
		byte[] data;
		
		try
		{
			data = parameter.getBytes("US-ASCII");
			appendRaw(data);
		} 
		catch (Exception e) 
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private void appendRaw(byte[] data)
	{
		System.arraycopy(data, 0, message, messageLength, data.length);
		messageLength += data.length;
	}
	
	public String toString()
	{
		String stringMessage = "";
		
		for(int i = 0; i < parameterCount; i++)
		{
			stringMessage = stringMessage + hsmParameters[i].toString() + "\n";
		}
		
		return stringMessage;
	}
	
	abstract public void defaultSettings();
}
