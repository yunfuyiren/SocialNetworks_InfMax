package com.AsymMaxInf.entity;


 /**
 * @author wangyang
 * 边类
 * 此处图的E为有向边，对于(a,b)间的相互连接，存两次a->b,b->a
 */
public class Edge 
 { 
	public int srcNode; 
	public int desNode;
	public double edgeWeight;   //边固有的权值
	public Edge(int src,int des)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=1;
	}
	public Edge(int src,int des,double w)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=w;
	}
	public Edge(Edge e)
	{
		srcNode=e.srcNode;
		desNode=e.desNode;
		edgeWeight=e.edgeWeight;
	}
}
