package com.AsymMaxInf.entity;

import java.util.HashMap;

public class NodeEntity {
	int id;						  //�ڵ�id
	public int degree;			  //�ڵ����
	public double threshold;	  //��ֵ
	public double accuInfluence;  //�ۻ�Ӱ����
	public HashMap<Integer,Double> inEdges; //��߼���,ÿ�����϶�Ӧ��ΪȨֵ
	public HashMap<Integer,Double> outEdges;	//���߼���
	public NodeEntity(int d)
	{
		inEdges=new HashMap<Integer,Double>();	//DoubleΪP(w,v)�ı�Ȩ�����籾��ı�Ȩ��һ��
		outEdges=new HashMap<Integer,Double>();
		accuInfluence=0;
		degree=d;
	}
	
}
