package com.smartbt.vtsuite.servercommon.hsm;

import com.smartbt.vtsuite.servercommon.hsm.message.HSMDecryptSymmetric;
import com.smartbt.vtsuite.servercommon.hsm.message.HSMPerformDiagnostics;
import java.util.Arrays;


public class HSMCaller 
{
	public static int RESPONSE_SIZE = 8;
	public static int RESPONSE_SIZE_WITH_LENGTH = 12;
	
	private HSMPerformDiagnostics diagMessage;
	private HSMDecryptSymmetric decryptSymmetric;
	
	HSMChannel channel;
	
	byte[] byteMessage, response;
	
	public HSMCaller(String address, int port)
	{
		channel = new HSMChannel(address, port);
		diagMessage = new HSMPerformDiagnostics();
		decryptSymmetric = new HSMDecryptSymmetric();
	}
	
	public void initHSMTransmission()
	{
		channel.connect();
	}
	
	public void endHSMTransmission()
	{
		channel.disconnect();
	}
	
	public byte[] diagnose() throws Exception
	{
		byteMessage = diagMessage.exportMessage();
		
		return channel.exchange(byteMessage);
	}
	
	public String decryptSymmetric(String bdk, String encryptedBlock, String serialNumber, String initializationVector) throws Exception
	{
		decryptSymmetric.defaultSettings();
		decryptSymmetric.bdk.setValue(bdk);
		decryptSymmetric.serialNumber.setValue(serialNumber.toUpperCase());
		decryptSymmetric.initializationVector.setValue(initializationVector);
		decryptSymmetric.encryptedBlock.setRawParameter(hexStringToByteArray(encryptedBlock));
		
		byteMessage = decryptSymmetric.exportMessage();
                System.out.print(decryptSymmetric);
		response = channel.exchange(byteMessage);
		return bytesToHexString(Arrays.copyOfRange(response, RESPONSE_SIZE_WITH_LENGTH + 16, response.length));
	}
	
	public static byte[] hexStringToByteArray(String s)
	{
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    
	    for (int i = 0; i < len; i += 2)
	    {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    
	    return data;
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
