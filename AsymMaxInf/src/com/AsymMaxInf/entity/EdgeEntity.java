package com.AsymMaxInf.entity;

/*��ʵ���������ͼ������ͼ�Ļ�ҪsrcNode��desNode������*/
 public class EdgeEntity 
 { 
	int srcNode; 
	int desNode;
	float edgeWeight;
	public EdgeEntity(int src,int des)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=1;
	}
	public EdgeEntity(int src,int des,float w)
	{
		srcNode=src;
		desNode=des;
		edgeWeight=w;
	}
	
}
