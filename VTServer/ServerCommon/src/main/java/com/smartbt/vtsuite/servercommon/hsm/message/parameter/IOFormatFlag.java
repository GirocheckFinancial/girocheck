package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class IOFormatFlag extends HSMParameter
{
	public IOFormatFlag()
	{
		parameterDescription = "Input/output Format Flag";
	}
	
	public void binary()
	{
		parameterValue = "0";
		valueDescription =  "Binary";
	}
	
	public void hexEncodedBinary()
	{
		parameterValue = "1";
		valueDescription =  "Hex Encoded Binary";
	}
	
	public void asciiText()
	{
		parameterValue = "2";
		valueDescription =  "ASCII Text";
	}
}
