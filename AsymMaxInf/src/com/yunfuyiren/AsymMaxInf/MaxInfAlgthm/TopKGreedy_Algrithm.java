package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yunfuyiren.AsymMaxInf.TranModel.PageRankPModel;
import com.yunfuyiren.AsymMaxInf.entity.PRPGraph;

public class TopKGreedy_Algrithm {
	PRPGraph G;
	public final int M=2;		//重复计算次数
	public ArrayList<Integer> S;	//输出种子集合
	int K;		//种子集合大小
	PageRankPModel PRPModel;
	public TopKGreedy_Algrithm(PRPGraph g,int k){
		G=g;	
		S=new ArrayList<Integer>();
		K=k;
		PRPModel=new PageRankPModel(G);	//初始化传播模型
	}
	public void Greedy_Algrithm()
	{
		
		for(int i=0;i<K;i++)
		{
			HashMap<Integer,Double> maxMB=new HashMap<Integer,Double>();
			
			//对每一个节点
			for(int j=0;j<G.inEdges.size();j++)
			{
				if(!S.contains(j+1))
				{
					ArrayList<Integer> SS=new ArrayList<Integer>(S);
					SS.add(j+1);
					double sv=0;
					for(int t=0;t<M;t++)  //计算t时刻的节点边际效益
					{
						int rs0=PRPModel.Activiting(S);
						int rs1=PRPModel.Activiting(SS);
						sv+=(double)(rs1-rs0);/*结点v在所有时刻的边际效益总和*/
					}
					sv=sv/M; //节点的平均边际效益
					maxMB.put(j+1, sv);
					SS.clear();
					SS=null;
				}
			}
			double tempmax=0;
			int tempindex=0;
			Iterator<Entry<Integer, Double>> iter=maxMB.entrySet().iterator();
			while(iter.hasNext())
			{
				Map.Entry<Integer, Double> entry=
						(Map.Entry<Integer, Double>)iter.next();
				if(tempmax<entry.getValue())
				{
					tempindex=entry.getKey();
					tempmax=entry.getValue();
				}
			}
			S.add(tempindex);
		}
	}

//	public 
}
