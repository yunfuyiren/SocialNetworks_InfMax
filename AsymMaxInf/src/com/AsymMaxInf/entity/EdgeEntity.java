package com.AsymMaxInf.entity;


 /**
 * @author wangyang
 * 边类
 * 此处图的E为有向边，对于(a,b)间的相互连接，存两次a->b,b->a
 */
public class EdgeEntity 
 { 
	public int srcNode; 
	public int desNode;
	public double edgeWeight;   //边固有的权值
	public EdgeEntity(int src,int des)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=1;
	}
	public EdgeEntity(int src,int des,double w)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=w;
	}
	public EdgeEntity(EdgeEntity e)
	{
		srcNode=e.srcNode;
		desNode=e.desNode;
		edgeWeight=e.edgeWeight;
	}
}
