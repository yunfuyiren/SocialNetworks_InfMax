package com.yunfuyiren.AsymMaxInf.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.yunfuyiren.AsyMaxInf.Interface.SetGraphAttributes;

/**
 * @author wang
 * LTGraph对Graph的继承
 * 定义节点阈值，完成对节点阈值以及边权b(v,w)赋值
 */
public class LTGraph extends Graph implements SetGraphAttributes{
	public ArrayList<Double>	nodeThreshold;	//节点阈值
	
	public LTGraph(String fileName) throws IOException
	{
		super(fileName);	//子类调用父类构造函数
		nodeThreshold=new ArrayList<Double>();
	}
	/*设置节点阈值*/
	public void SetThreshold()
	{
		Random r=new Random();
		for(int i=0;i<nodeNum;i++)
		{
			double v=r.nextDouble();
			nodeThreshold.add(v);
		}
	}
	
	/*设置边权: 对每条边的权值定义为des节点的入度的倒数1/n*/
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		/*节点入边集合作为迭代器*/
		Iterator<ArrayList<Edge>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*对每条边的权值定义为des节点的入度的倒数1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}
}
