package com.AsyMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;

import com.AsymMaxInf.TranModel.IndependCasecadeModel;

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
//		LineThresholdModel LT=new LineThresholdModel(fileName);
//		int n=LT.Activiting(Init);
		IndependCasecadeModel IC=new IndependCasecadeModel(fileName);
		int n=IC.Activiting(Init);
		System.out.print(n);
	}
}
