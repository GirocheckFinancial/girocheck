package com.smartbt.vtsuite.servercommon.hsm.message;

import com.smartbt.vtsuite.servercommon.hsm.message.parameter.MiscParameter;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.ModeFlag;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.DataLength;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.HSMParameter;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.VariantKeyType;
import com.smartbt.vtsuite.servercommon.hsm.message.parameter.IOFormatFlag;

public class HSMDecryptSymmetric extends HSMMessageBuilder
{
	public ModeFlag modeFlag;
	public IOFormatFlag inputFormatFlag;
	public IOFormatFlag outputFormatFlag;
	public VariantKeyType keyType;
	public MiscParameter bdk, serialNumber, initializationVector;
	protected DataLength encryptedBlockLength;
	public HSMParameter encryptedBlock;
	
	public HSMDecryptSymmetric()
	{
		command.decryptSymmetric();
		
		modeFlag = new ModeFlag();
		inputFormatFlag = new IOFormatFlag();
		outputFormatFlag = new IOFormatFlag();
		keyType = new VariantKeyType();
		bdk = new MiscParameter("BDK");
		serialNumber = new MiscParameter("Terminal Serial Number");
		initializationVector = new MiscParameter("Initialization Vector");
		encryptedBlock = new HSMParameter("Encrypted Block");
		encryptedBlockLength = new DataLength(encryptedBlock);

		addParameter(modeFlag);
		addParameter(inputFormatFlag);
		addParameter(outputFormatFlag);
		addParameter(keyType);
		addParameter(bdk);
		addParameter(new MiscParameter("906", "KSN Descriptor"));
		addParameter(serialNumber);
		addParameter(initializationVector);
		addParameter(encryptedBlockLength);
		addParameter(encryptedBlock);
		
		encryptedBlockLength.hex();
	}
	
	public void defaultSettings()
	{
		modeFlag.CBC();
		inputFormatFlag.binary();
		outputFormatFlag.binary();
		keyType.bidirectionalBDK();
	}
}
