package com.AsymMaxInf.entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang
 * 继承Graph
 * 包括对节点阈值赋值、节点之间关系，邻居影响力权重b(v,w)
 */
public class LTGraph extends Graph {
	ArrayList<Double>	nodeThreshold;	//节点阈值
	ArrayList<ArrayList<Double>>	inEdgesLTWeight; //每个节点的入边邻接表权重，
	ArrayList<ArrayList<Double>>	outEdgesLTWeight;//每个节点的出边邻接表权重。
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
		inEdgesLTWeight=new ArrayList<ArrayList<Double>>();
		outEdgesLTWeight=new ArrayList<ArrayList<Double>>();
	}
	/*节点阈值设置*/
	public void SetThreshold()
	{
		for(int i=0;i<nodeNum;i++)
		{
			double v=Math.random();
			nodeThreshold.add(v);
		}
	}
	/*边权设置*/
	public void SetLTWeight()
	{
		//对入边权与出边权的addAll是为了与 inEdges与outEdges形式对应方便
		inEdgesLTWeight.addAll(inEdgesWeight);
		outEdgesLTWeight.addAll(outEdgesWeight);
		
		Iterator<ArrayList<Integer>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			//每一次循环赋权，既对des出边改变，又对src的入边改变
			ArrayList<Integer> elem=new ArrayList<Integer>();
			int n=elem.size();
			
			//对src出边的List赋权
			ArrayList<Double> outElem=new ArrayList<Double>();
			for(int i=0;i<n;i++)
				outElem.add((double)1/n);
			
			//对应的，对des入边的List赋权
			Iterator<Integer> iter2=
		}
	}
}
