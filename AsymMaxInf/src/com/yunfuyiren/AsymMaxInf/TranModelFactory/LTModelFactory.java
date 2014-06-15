package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.TranModelPro.LTModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class LTModelFactory implements TransferModelFactory{

	@Override
	public TransferModel CreatingModel() {
		// TODO Auto-generated method stub
		TransferModel tm=new LTModel();
		return tm;
	}

}
