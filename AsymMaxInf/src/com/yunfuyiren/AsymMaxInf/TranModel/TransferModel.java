package com.yunfuyiren.AsymMaxInf.TranModel;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.entity.Graph;

abstract class TransferModel {
	ArrayList<Integer> R;		//当前已激活节点集合
	int count;			//当前活跃节点集合的大小
	public TransferModel(Graph graph)
	{
		
	}
	public TransferModel()
	{
		
	}
	public abstract int Activiting(ArrayList<Integer> Init);
}
