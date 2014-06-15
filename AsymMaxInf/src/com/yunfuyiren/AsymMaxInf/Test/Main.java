package com.yunfuyiren.AsymMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphFactory.GraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.ICGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.LTGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.PRPGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.ICGraph;
import com.yunfuyiren.AsymMaxInf.GraphPro.LTGraph;
import com.yunfuyiren.AsymMaxInf.GraphPro.PRPGraph;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.TopKGreedy_Algrithm;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.ICModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.LTModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.PRPModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.TransferModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelPro.ICModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.LTModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.PRPModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class Main {
	public static void main(String[] args) throws IOException
	{
//		String a="1 3 3.5";
//		String[] b;
//		b=a.split(" ");
//		System.out.println(b);
//		ArrayList<Integer> elem=new ArrayList<Integer>();
//		elem.add(0);
//		System.out.println(elem);
	
//		ArrayList<ArrayList<Integer>> inEdges=new ArrayList<ArrayList<Integer>>(); 
//		ArrayList<Integer> inEdge;
//		for(int i=0;i<4;i++)
//		{
//			 inEdge=new ArrayList<Integer>();
//			 inEdges.add(inEdge);
//		}
//		inEdge=new ArrayList<Integer>();
//		inEdge.add(1);
//		inEdges.add(inEdge);
//		for(int i=0;i<5;i++)
//		{
//			ArrayList<Integer> e=inEdges.get(i);
//			System.out.println(e);
//			System.out.println(e.size());
//		}
		
	
		ArrayList<Integer> Init =new ArrayList<Integer>();
		Init.add(2);
		String fileName="test.txt";
		TopKGreedy_Algrithm top;
		
		//线性阈值模型
//		LTGraph G=new LTGraph(fileName);
//		LineThresholdModel LT=new LineThresholdModel(G);
//		top=new TopKGreedy_Algrithm(G,G,LT,2,1000);			
//		//级联模型
//		ICGraph G2=new ICGraph(fileName);
//		IndependCasecadeModel IC=new IndependCasecadeModel(G2);
//		top=new TopKGreedy_Algrithm(G2,G2,IC,2,1000);		
		//PageRank模型
		GraphFactory factory=new ICGraphFactory();
		Graph G3=factory.CreateGraph(fileName);
		TransferModelFactory fm3=new ICModelFactory();
		TransferModel model=fm3.CreatingModel();
		top=new TopKGreedy_Algrithm(G3,model,2,2);
		
		top.Greedy_Algrithm();
		System.out.println(top.S);
		System.out.println(top.Influence);
//		MaxDegreeHeuristic MaxD=new MaxDegreeHeuristic(fileName);
//		ArrayList<Integer> R=MaxD.MaxDegreeList(3);

	}
}
