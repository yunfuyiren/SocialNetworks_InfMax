package com.AsymMaxInf.entity;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author wang
 * �̳�Graph
 * �����Խڵ���ֵ��ֵ���ڵ�֮����ϵ���ھ�Ӱ����Ȩ��b(v,w)
 */
public class LTGraph extends Graph {
	public ArrayList<Double>	nodeThreshold;	//�ڵ���ֵ
	
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
	}
	/*�ڵ���ֵ����*/
	public void SetThreshold()
	{
		Random r=new Random(55);
		for(int i=0;i<nodeNum;i++)
		{
			double v=r.nextDouble();
			nodeThreshold.add(v);
		}
	}
	/*��Ȩ���¸�ֵ����*/
	public void SetLTWeight()
	{
	
		/*����Ȩ���㣬��Ϊ����Ȩ������Ȩ�õ�����ͬ���ڴ棬�޸ĳ��ߵ�Ȩֵ����ͬ���޸������ߵ�Ȩֵ*/
		Iterator<ArrayList<Edge>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			/*ÿһ��ѭ��Ϊ��ÿһ���ڵ��ĳ��߶��и�Ȩ*/
			ArrayList<Edge>e=iter1.next();
			
			//��src���ߵ�List�޸�Ȩֵ����Ϊsrc�ĳ���Edge������des������Edge��������ͬһ���ڴ棬�����޸�һ�ξͺá�
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}
}
