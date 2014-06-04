package com.AsymMaxInf.entity;


 /**
 * @author wangyang
 * ����
 * �˴�ͼ��EΪ����ߣ�����(a,b)����໥���ӣ�������a->b,b->a
 */
public class EdgeEntity 
 { 
	public int srcNode; 
	public int desNode;
	public double edgeWeight;   //�߹��е�Ȩֵ
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
