package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public interface MaxInfAlgrithmInterface {
	void Cac_MaxInf();
	double Propagation_Process(TransferModel TM,ArrayList<Integer> Init);
}
