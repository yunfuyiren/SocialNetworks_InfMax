package com.AsymMaxInf.entity;

/*本实验针对有向图，无向图的话要srcNode与desNode算两次*/
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
