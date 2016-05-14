package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

import java.util.Arrays;

public class HSMParameter 
{
	public boolean isRaw;
	protected byte[] rawData;
	protected String parameterDescription, valueDescription, parameterValue;

	public HSMParameter()
	{
		parameterDescription = "HSM Parameter";
	}
	
	public HSMParameter(String valueDescription)
	{
		this.valueDescription = valueDescription;
	}
	
	public String toString()
	{
		if(isRaw)
		{
			parameterDescription = "Raw Data";
			return parameterDescription + ": " + valueDescription + "(" + rawData.length + ") = " + parameterValue;
		}
		else
			return parameterDescription + ": " + valueDescription + "(" + parameterValue.length() + ") = " + parameterValue;
	}
	
	public String getStringParameter()
	{
		if(parameterValue == null)
		{
			System.out.print("\nWarning, empty parameter.\n");
			return "";
		}
		
		return parameterValue;
	}
	
	public byte[] getRawParameter()
	{
		return rawData;
	}
	
	public void setRawParameter(byte data[])
	{
		isRaw = true;
		rawData = data;
		parameterValue = Arrays.toString(data);
	}
}
