package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.TranModelPro.LTweakenModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class LTweakenModelFactory implements TransferModelFactory{

	@Override
	public TransferModel CreatingModel() {
		// TODO �Զ����ɵķ������
		TransferModel tm=new LTweakenModel();
		return tm;
	}

}
