package com.yunfuyiren.AsyMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.TopKGreedy_Algrithm;
import com.yunfuyiren.AsymMaxInf.TranModel.IndependCasecadeModel;
import com.yunfuyiren.AsymMaxInf.TranModel.LineThresholdModel;
import com.yunfuyiren.AsymMaxInf.TranModel.PageRankPModel;
import com.yunfuyiren.AsymMaxInf.entity.ICGraph;
import com.yunfuyiren.AsymMaxInf.entity.LTGraph;
import com.yunfuyiren.AsymMaxInf.entity.PRPGraph;

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
		
//		//线性阈值模型
//		LTGraph G=new LTGraph();
//		G.ReadFromFile(fileName);
//		LineThresholdModel LT=new LineThresholdModel(G);
//		int n=LT.Activiting(Init);
//		//级联模型
		ICGraph G2=new ICGraph(fileName);
		IndependCasecadeModel IC=new IndependCasecadeModel(G2);
		int n2=IC.Activiting(Init);
//		//PageRank模型
//		PRPGraph G3=new PRPGraph(fileName);
//
//		TopKGreedy_Algrithm PR=new TopKGreedy_Algrithm(G3,2);
//		PR.Greedy_Algrithm();
//		MaxDegreeHeuristic MaxD=new MaxDegreeHeuristic(fileName);
//		ArrayList<Integer> R=MaxD.MaxDegreeList(3);
		System.out.print(n2);
		
	}
}
