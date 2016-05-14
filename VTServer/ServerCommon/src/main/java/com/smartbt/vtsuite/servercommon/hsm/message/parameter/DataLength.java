package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class DataLength extends HSMParameter
{
	public static final int NUM_OF_LENGTH_DIGITS = 4;
	
	String base;
	HSMParameter parameter;
	
	public DataLength(HSMParameter parameter)
	{
		this.parameter = parameter;
	}
	
	public void decimal()
	{
		base = "decimal";
	}
	
	public void hex()
	{
		base = "hex";
	}
	
	public String getStringParameter()
	{
		parameterDescription = "Length of Data (in " + base + ")";
		valueDescription = parameter.valueDescription;
		
		if(parameter.isRaw)
			parameterValue = getLengthString(parameter.getRawParameter().length);
		else
			parameterValue = getLengthString(parameter.getStringParameter().length());
		
		while(parameterValue.length() < NUM_OF_LENGTH_DIGITS)
			parameterValue = "0" + parameterValue;
		
		return parameterValue;
	}
	
	private String getLengthString(int length)
	{
		if(base.equals("decimal"))
			return Integer.toString(length);
		else if(base.equals("hex"))
			return Integer.toHexString(length);
		
		return "Error, no base set";
	}
}
