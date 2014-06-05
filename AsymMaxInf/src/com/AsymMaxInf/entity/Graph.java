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
	ArrayList<ArrayList<Edge>> inEdges; //每个节点的入边邻接表
	ArrayList<ArrayList<Edge>> outEdges; //每个节点的出边邻接表
	
	HashMap<String,Integer> nodeMapping;	//节点映射
	int nodeNum;		//节点数;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Edge>>();
		outEdges=new ArrayList<ArrayList<Edge>>();
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
			ArrayList<Edge> elem=new ArrayList<Edge>();
			inEdges.add(elem);
			elem=new ArrayList<Edge>();
			outEdges.add(elem);

			//节点阈值在各个图的子类中赋值			
		}
		while((data=br.readLine())!=null)
		{
			String[] d=data.split(" ");
			int src=Integer.valueOf(d[0]);
			int des=Integer.valueOf(d[1]);
			double val=Double.valueOf(d[2]);
			//入边和出边List存的是相同的内存的数据，这里用的是引用。
			Edge edge=new Edge(src,des,val);
			inEdges.get(des).add(edge);
			outEdges.get(src).add(edge);			
		}
	}
}
