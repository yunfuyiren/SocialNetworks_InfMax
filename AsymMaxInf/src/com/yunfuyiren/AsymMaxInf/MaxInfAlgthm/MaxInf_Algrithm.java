package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class MaxInf_Algrithm {
	Graph G;			//网络图
	TransferModel TM;   //传播模型
	int M;		//重复计算次数
	int K;		//种子集合大小,Top K的K
	
	/*输出集合*/
	public ArrayList<Integer> S;	//输出种子集合
	public double Influence;    //输出影响力大小
}
