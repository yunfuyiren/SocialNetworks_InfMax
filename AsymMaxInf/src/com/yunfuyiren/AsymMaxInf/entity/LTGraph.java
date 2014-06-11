package com.yunfuyiren.AsymMaxInf.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author wang
 * LTģ�Ͷ�Graph�ļ̳�
 * ����ڵ���ֵ����ɶԽڵ���ֵ�Լ���Ȩb(v,w)��ֵ
 */
public class LTGraph extends Graph {
	public ArrayList<Double>	nodeThreshold;	//�ڵ���ֵ
	
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
	}
	/*���ýڵ���ֵ*/
	public void SetThreshold()
	{
		Random r=new Random();
		for(int i=0;i<nodeNum;i++)
		{
			double v=r.nextDouble();
			nodeThreshold.add(v);
		}
	}
	/*���ñ�Ȩ*/
	public void SetLTWeight()
	{
		/*�ڵ���߼�����Ϊ������*/
		Iterator<ArrayList<Edge>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*��ÿ���ߵ�Ȩֵ����Ϊdes�ڵ����ȵĵ���1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}
}
