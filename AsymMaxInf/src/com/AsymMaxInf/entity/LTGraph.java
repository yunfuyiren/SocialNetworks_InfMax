package com.AsymMaxInf.entity;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author wang
 * 继承Graph
 * 包括对节点阈值赋值、节点之间关系，邻居影响力权重b(v,w)
 */
public class LTGraph extends Graph {
	public ArrayList<Double>	nodeThreshold;	//节点阈值
	
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
	}
	/*节点阈值设置*/
	public void SetThreshold()
	{
		Random r=new Random(55);
		for(int i=0;i<nodeNum;i++)
		{
			double v=r.nextDouble();
			nodeThreshold.add(v);
		}
	}
	/*边权重新赋值设置*/
	public void SetLTWeight()
	{
	
		/*出边权计算，因为出边权与入边权用的是相同的内存，修改出边的权值，则同样修改了入边的权值*/
		Iterator<ArrayList<Edge>> iter1=outEdges.iterator();
		while(iter1.hasNext())
		{
			/*每一次循环为给每一个节点的出边队列赋权*/
			ArrayList<Edge>e=iter1.next();
			
			//对src出边的List修改权值，因为src的出边Edge对象与des的入边Edge对象引用同一块内存，所以修改一次就好。
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}
}
