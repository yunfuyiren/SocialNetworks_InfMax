package com.AsymMaxInf.entity;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang
 * �̳�Graph
 * �����Խڵ���ֵ��ֵ���ڵ�֮���ϵ���ھ�Ӱ����Ȩ��b(v,w)
 */
public class LTGraph extends Graph {
	ArrayList<Double>	nodeThreshold;	//�ڵ���ֵ
	
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
	}
	/*�ڵ���ֵ����*/
	public void SetThreshold()
	{
		for(int i=0;i<nodeNum;i++)
		{
			double v=Math.random();
			nodeThreshold.add(v);
		}
	}
	/*��Ȩ���¸�ֵ����*/
	public void SetLTWeight()
	{
	
		/*����Ȩ���㣬��Ϊ����Ȩ�����Ȩ�õ�����ͬ���ڴ棬�޸ĳ��ߵ�Ȩֵ����ͬ���޸�����ߵ�Ȩֵ*/
		Iterator<ArrayList<Edge>> iter1=outEdges.iterator();
		while(iter1.hasNext())
		{
			/*ÿһ��ѭ��Ϊ��ÿһ���ڵ�ĳ��߶��и�Ȩ*/
			ArrayList<Edge>e=iter1.next();
			
			//��src���ߵ�List��Ȩ
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=1/n;
			
		}
	}
}
