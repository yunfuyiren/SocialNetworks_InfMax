package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.TranModelPro.ICModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class ICModelFactory implements TransferModelFactory{

	@Override
	public  TransferModel CreatingModel() {
		// TODO Auto-generated method stub
		TransferModel tm=new ICModel();
		return tm;
	}
}