package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;


public abstract class TransferModel implements SetModelAttributesInterface{
	ArrayList<Integer> R;		//当前已激活节点集合
	int count;			//当前活跃节点集合的大小
	Graph G;			//输入模型的网络图
	double total_inf; 	//网络总的影响力 
	public TransferModel(Graph g)
	{
		G=g;
		System.out.println("Model构造函数");
	}
}
