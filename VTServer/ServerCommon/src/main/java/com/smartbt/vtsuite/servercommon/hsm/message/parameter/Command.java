package com.smartbt.vtsuite.servercommon.hsm.message.parameter;

public class Command extends HSMParameter
{
	public Command()
	{
		parameterDescription = "Command";
	}
	
	// Diagnostic commands
	public void performDiagnostics()
	{
		parameterValue = "NC";
		valueDescription =  "Perform Diagnostics";
	}
	
	public void decryptSymmetric()
	{
		parameterValue = "M2";
		valueDescription =  "Decrypt a Message With a Symetric Key";
	}
}
