package com.smartbt.vtsuite.servercommon.hsm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class HSM_DS 
{
//	static HSMCaller hsm = new HSMCaller();
//	private static BufferedReader _in = new BufferedReader(new InputStreamReader(System.in));
//	
//	public static void main(String[] args)
//	{
//		int port;
//		String bdk, serialNumber, address, blockString, initializationVector;
//		byte[] encryptedBlock, result;
//		
//		try 
//		{
//			/*
//			System.out.print("Please enter address\n");
//			address = _in.readLine();
//			System.out.print("Please enter port\n");
//			port = Integer.parseInt(_in.readLine());
//			System.out.print("Please enter bdk\n");
//			bdk = _in.readLine();
//			System.out.print("Please enter encrypted message\n");
//			encryptedBlock = _in.readLine().getBytes();
//			System.out.print("Please enter serial number\n");
//			serialNumber = _in.readLine();
//			
//			*/
//			//clear bdk = "0123456789ABCDEF FEDCBA9876543210";
//			bdk = "U8E3D3E2FD5919657F05A1AA90D32A014";
//			serialNumber = "00002600110012E00005";
//			blockString = "6AC9C11F3C449EF33502EAD315A868880247BE1F3D1E46F923409B25884D3" +
//						  "6C6E4917EAC8B34679E7D6E4CBD6D6D42E9862A4CADD16E3DC5BE7CC3CA8B" +
//						  "8FDF58A2F6B4233C1E94CE7061805BD687D9BDB588EA2F58BA8252533D051" +
//						  "84ED52B5563647F8CAB09FD492F67BFE029625783AB5ECFE3BAC75B8D";
//			encryptedBlock = hexStringToByteArray(blockString);
//			initializationVector = "0000000000000000";
//			
//			//hsm.channel.setAddressAndPort(address, port);
//			hsm.initHSMTransmission();
//			result =  hsm.decryptSymmetric(bdk, "encryptedBlock", serialNumber, initializationVector).getBytes();
//			hsm.endHSMTransmission();
//			System.out.print("\n\nResult (ascii): \n" + new String(result));
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			hsm.endHSMTransmission();
//		}
//	}
//	
//	public static byte[] hexStringToByteArray(String s)
//	{
//	    int len = s.length();
//	    byte[] data = new byte[len / 2];
//	    
//	    for (int i = 0; i < len; i += 2)
//	    {
//	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//	                             + Character.digit(s.charAt(i+1), 16));
//	    }
//	    
//	    return data;
//	}
//	
//	public static String bytesToHexString(byte[] bytes) {
//	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
//	    char[] hexChars = new char[bytes.length * 2];
//	    int v;
//	    for ( int j = 0; j < bytes.length; j++ ) {
//	        v = bytes[j] & 0xFF;
//	        hexChars[j * 2] = hexArray[v >>> 4];
//	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//	    }
//	    return new String(hexChars);
//	}
}
