package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class ModeFlag extends HSMParameter
{
	public ModeFlag()
	{
		parameterDescription = "Decryption ModeFlag";
	}
	
	public void ECB()
	{
		parameterValue = "00";
		valueDescription =  "ECB";
	}
	
	public void CBC()
	{
		parameterValue = "01";
		valueDescription =  "CBC";
	}
	
	public void CFBB()
	{
		parameterValue = "02";
		valueDescription =  "CFBB";
	}
	
	public void CFB64()
	{
		parameterValue = "03";
		valueDescription =  "CFB64";
	}
}
