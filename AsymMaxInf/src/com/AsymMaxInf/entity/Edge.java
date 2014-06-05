package com.AsymMaxInf.entity;


 /**
 * @author wangyang
 * ����
 * �˴�ͼ��EΪ����ߣ�����(a,b)����໥���ӣ�������a->b,b->a
 */
public class Edge 
 { 
	public int srcNode; 
	public int desNode;
	public double edgeWeight;   //�߹��е�Ȩֵ
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
