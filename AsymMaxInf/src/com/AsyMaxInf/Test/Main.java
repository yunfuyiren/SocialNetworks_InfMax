package com.AsyMaxInf.Test;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args)
	{
//		String a="1 3 3.5";
//		String[] b;
//		b=a.split(" ");
//		System.out.println(b);
//		ArrayList<Integer> elem=new ArrayList<Integer>();
//		elem.add(0);
//		System.out.println(elem);
		ArrayList<ArrayList<Integer>> inEdges=new ArrayList<ArrayList<Integer>>(); 
		ArrayList<Integer> inEdge;
		int n=5;
		for(int i=0;i<4;i++)
		{
			 inEdge=new ArrayList<Integer>();
			 inEdges.add(inEdge);
		}
		 inEdge=new ArrayList<Integer>();
		 inEdge.add(0);
		inEdges.add(inEdge);
		System.out.print(inEdges);
	}
}
