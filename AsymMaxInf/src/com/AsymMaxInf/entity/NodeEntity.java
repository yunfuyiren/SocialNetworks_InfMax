package com.AsymMaxInf.entity;

import java.util.HashMap;

public class NodeEntity {
	int id;						  //节点id
	public int degree;			  //节点度数
	public double threshold;	  //阈值
	public double accuInfluence;  //累积影响力
	public HashMap<Integer,Double> inEdges; //入边集合,每条边上对应的为权值
	public HashMap<Integer,Double> outEdges;	//出边集合
	public NodeEntity(int d)
	{
		inEdges=new HashMap<Integer,Double>();	//Double为P(w,v)的边权与网络本身的边权不一样
		outEdges=new HashMap<Integer,Double>();
		accuInfluence=0;
		degree=d;
	}
	
}
