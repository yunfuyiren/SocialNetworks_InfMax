package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.LTModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class LTModelFactory implements TransferModelFactory{

	@Override
	public TransferModel CreatingModel(Graph g) {
		// TODO Auto-generated method stub
		TransferModel tm=new LTModel(g);
		return tm;
	}
}
