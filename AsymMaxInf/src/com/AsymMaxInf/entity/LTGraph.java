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
	ArrayList<ArrayList<Double>>	inEdgesLTWeight; //ÿ���ڵ������ڽӱ�Ȩ�أ�
	ArrayList<ArrayList<Double>>	outEdgesLTWeight;//ÿ���ڵ�ĳ����ڽӱ�Ȩ�ء�
	public LTGraph()
	{
		nodeThreshold=new ArrayList<Double>();
		inEdgesLTWeight=new ArrayList<ArrayList<Double>>();
		outEdgesLTWeight=new ArrayList<ArrayList<Double>>();
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
	/*��Ȩ����*/
	public void SetLTWeight()
	{
		//�����Ȩ�����Ȩ��addAll��Ϊ���� inEdges��outEdges��ʽ��Ӧ����
		inEdgesLTWeight.addAll(inEdgesWeight);
		outEdgesLTWeight.addAll(outEdgesWeight);
		
		Iterator<ArrayList<Integer>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			//ÿһ��ѭ����Ȩ���ȶ�des���߸ı䣬�ֶ�src����߸ı�
			ArrayList<Integer> elem=new ArrayList<Integer>();
			int n=elem.size();
			
			//��src���ߵ�List��Ȩ
			ArrayList<Double> outElem=new ArrayList<Double>();
			for(int i=0;i<n;i++)
				outElem.add((double)1/n);
			
			//��Ӧ�ģ���des��ߵ�List��Ȩ
			Iterator<Integer> iter2=
		}
	}
}
