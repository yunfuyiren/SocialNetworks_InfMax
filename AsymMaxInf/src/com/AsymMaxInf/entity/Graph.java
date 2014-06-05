package com.AsymMaxInf.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wang
 *	图的定义
 *  邻接表
 */
public class Graph {
	ArrayList<ArrayList<Integer>> inEdges; //每个节点的入边邻接表
	ArrayList<ArrayList<Integer>> outEdges; //每个节点的出边邻接表
	ArrayList<ArrayList<Double>>	inEdgesWeight; //每个节点的入边邻接表权重，
	ArrayList<ArrayList<Double>>	outEdgesWeight;//每个节点的出边邻接表权重。
	HashMap<String,Integer> nodeMapping;	//节点映射
	int nodeNum;		//节点数;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Integer>>();
		outEdges=new ArrayList<ArrayList<Integer>>();
		inEdgesWeight=new ArrayList<ArrayList<Double>>();
		outEdgesWeight=new ArrayList<ArrayList<Double>>();
		nodeMapping=new HashMap<String,Integer>();
	}
	/*文件格式必须为第一行是节点数，第二行开始为 srcnode desnode weight*/
	public void ReadFromeFile(String FileName) throws IOException
	{
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new FileReader(FileName));
		String data=br.readLine();
		nodeNum=Integer.parseInt(data);
		//获得节点数后，对网络初始化
		for(int i=0;i<nodeNum;i++)
		{
			ArrayList<Integer> elem=new ArrayList<Integer>();
			elem.add(0);
			inEdges.add(elem);
			elem=new ArrayList<Integer>();
			elem.add(0);
			outEdges.add(elem);
			ArrayList<Double> elem2=new ArrayList<Double>();
			elem2.add((double) 0);
			inEdgesWeight.add(elem2);
			elem2=new ArrayList<Double>();
			elem2.add((double) 0);
			outEdgesWeight.add(elem2);
			//节点阈值在各个图的子类中赋值
			
		}
		while((data=br.readLine())!=null)
		{
			String[] d=data.split(" ");
			int src=Integer.valueOf(d[0]);
			int des=Integer.valueOf(d[1]);
			double val=Double.valueOf(d[2]);
			inEdges.get(des).add(src);
			outEdges.get(src).add(des);
			inEdgesWeight.get(des).add(val);
			outEdgesWeight.get(src).add(val);
		}
	}
}
