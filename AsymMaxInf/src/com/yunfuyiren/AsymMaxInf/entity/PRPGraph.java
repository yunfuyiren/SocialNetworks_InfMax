package com.yunfuyiren.AsymMaxInf.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.yunfuyiren.AsyMaxInf.Interface.SetGraphAttributes;

public class PRPGraph extends Graph implements SetGraphAttributes{
	public ArrayList<Double> pageRank;
	double q;	//阻尼系数
	public PRPGraph(String fileName) throws IOException{
		super(fileName);
		System.out.println("PRPGraph构造函数");
		pageRank=new ArrayList<Double>();
		q=0.85;
	}
	
	//直接给每个节点的PageRank初值设为1，然后迭代到满足一定条件为止。
	public void SetPageRank1()
	{
		double threshold=0.0001; //每次循环迭代时，总的pageRank值的变化阈值
		double calc_pr=10000;//保存在迭代时，pageRank变化累计值
		for(int i=0;i<nodeNum;i++)
		{
			pageRank.add((double) 1);  //初始阈值设定
		}
		int time=0;   //迭代次数
		while(calc_pr>threshold)
		{
			calc_pr=0;
			for(int i=0;i<nodeNum;i++)
			{
				double temp_pr=0;
				for(Edge e:inEdges.get(i))
				{
					temp_pr+=pageRank.get(e.srcNode-1)/outEdges.get(e.srcNode-1).size();
				}
				temp_pr=temp_pr*q+(1-q);//nodeNum;
				double pre_pr=pageRank.get(i);
				calc_pr+=Math.abs(temp_pr-pre_pr);
			
				pageRank.set(i, temp_pr);
			}
			time++;
		}
	}

	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		Iterator<ArrayList<Edge>> iter=inEdges.iterator();
		while(iter.hasNext())
		{
			ArrayList<Edge> edges =iter.next();
			double sum_pr=0;
			for(Edge e:edges)
			{
				sum_pr+=pageRank.get(e.srcNode-1);
			}
			for(Edge e:edges)
			{
				e.edgeWeight=pageRank.get(e.srcNode-1)/sum_pr;
			}
		}
	}

	@Override
	public void SetThreshold() {
		// TODO Auto-generated method stub
		
	}
}
