package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class MiscParameter extends HSMParameter
{
	public MiscParameter(String value, String description)
	{
		parameterDescription = "Misc. Parameter";
		valueDescription = description;
		
		if(value == null)
			parameterValue = "Warning, Misc Parameter Not Set";
		
		parameterValue = value;
	}
	
	public MiscParameter(String description)
	{
		parameterDescription = "Misc. Parameter";
		valueDescription = description;
	}
	
	public void setValue(String value)
	{
		parameterValue = value;
	}
}
