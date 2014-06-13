package com.yunfuyiren.AsymMaxInf.TranModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.yunfuyiren.AsyMaxInf.Interface.SetModelAttributes;
import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.PRPGraph;

public class PageRankPModel extends TransferModel implements SetModelAttributes{
	PRPGraph G;
	
	/*输入为一个PRPGraph*/
	public PageRankPModel(PRPGraph graph)
	{
		G=graph;
		G.SetPageRank1();
		System.out.println(G.pageRank);
		G.SetWeight();
	}
	/*传入参数是初始活跃节点集合*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>(Init);		   //将活跃节点加入R
		Set<Integer>S=new HashSet<Integer>();  //待激活节点集合,这里使用Set是为了防止存入相同节点
		count=0;  //激活节点数清零
		G.InitIsActive();
		//获取初始节点的粉丝节点的集合
		for(Integer I:Init)
		{
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode))		//如果该粉丝节点尚未被激活，加入待激活的队列
				{
					S.add(edge.desNode);
				}
			}	
		}
		Random r=new Random();
		while(true) //每次循环就是一个时间步
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			ArrayList<Double> pActive=new ArrayList<Double>(); 
			//激活概率序列，每次循环时，计算所有S集合中的待激活节点的激活概率，全部计算侯再进行激活操作
			//这样避免了如果两个节点之间有环，需要先确定一个节点的状态，而发生死锁的过程
			for(Integer I:S)
			{
				double p=0;
				//计算节点被激活的概率
				for(Edge e:G.inEdges.get(I-1))
				{
					if(G.isActive.get(e.srcNode-1))
					{
						p+=e.edgeWeight;
					}
				}
				pActive.add(p);
			}
			int i=0;  //为了保持在for循环时，pActive与G.isActive对应
			for(Integer I:S)
			{
				double ran=r.nextDouble();
				if(ran<pActive.get(i))
				{
					G.isActive.set(I-1,true);
					temp.add(I);
					count++;
				}
				i++;
			}
			
			//没有节点被激活，也就没有信息传递，影响力停止传递
			if(temp.isEmpty())
				break;
			
			//将 temp 中节点加入R,同时将R中所有节点粉丝节点放入S
			R.addAll(temp);
			S.clear();
			for(Integer I:temp)
			{
				for(Edge e:G.outEdges.get(I-1))
					if(G.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
			}
			temp=null;
		}		
		return count;
	}
	
}
