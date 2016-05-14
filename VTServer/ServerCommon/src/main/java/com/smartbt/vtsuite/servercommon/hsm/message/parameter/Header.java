package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class Header extends HSMParameter
{
	public static final String DEFAULT_HEADER = "1234";
	
	public Header()
	{
		parameterValue = DEFAULT_HEADER;
		parameterDescription = "Header";
		valueDescription = "Header Value";
	}
	
	public void setHeader(String header)
	{
		parameterValue = header;
	}
}
