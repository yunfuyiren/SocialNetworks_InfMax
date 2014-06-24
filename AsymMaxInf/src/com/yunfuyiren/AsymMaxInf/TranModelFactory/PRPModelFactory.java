package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.PRPModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class PRPModelFactory implements TransferModelFactory{

	@Override
	public TransferModel CreatingModel(Graph g) {
		// TODO Auto-generated method stub
		TransferModel fm=new PRPModel(g);
		return fm;
	}

}
