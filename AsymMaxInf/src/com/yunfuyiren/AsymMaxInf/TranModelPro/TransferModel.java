package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;


public class TransferModel implements SetModelAttributesInterface{
	ArrayList<Integer> R;		//当前已激活节点集合
	int count;			//当前活跃节点集合的大小
	public TransferModel(Graph g)
	{
		System.out.println("Model构造函数");
	}
	public TransferModel()
	{
		System.out.println("Model构造函数");
	}
	@Override
	public int Activiting(Graph g, ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		return 0;
	}
}
