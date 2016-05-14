package com.smartbt.vtsuite.servercommon.hsm.message;

public class HSMPerformDiagnostics extends HSMMessageBuilder
{
	public HSMPerformDiagnostics()
	{
		command.performDiagnostics();
	}

	public void defaultSettings()
	{
		
	}
}
