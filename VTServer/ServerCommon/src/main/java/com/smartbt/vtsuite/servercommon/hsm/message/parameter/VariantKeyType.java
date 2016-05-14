package com.smartbt.vtsuite.servercommon.hsm.message.parameter;


public class VariantKeyType extends HSMParameter
{
	public VariantKeyType()
	{
		parameterDescription = "Variant Key Type";
	}
	
	public void bidirectionalBDK()
	{
		parameterValue = "009";
		valueDescription = "BidirectionalBDK (Type 1)";
	}
	
	public void unidirectionalBDK()
	{
		parameterValue = "609";
		valueDescription = "UnidirectionalBDK (Type 2)";
	}
	
	public void IPEK()
	{
		parameterValue = "302";
		valueDescription = "IPEK";
	}
}
