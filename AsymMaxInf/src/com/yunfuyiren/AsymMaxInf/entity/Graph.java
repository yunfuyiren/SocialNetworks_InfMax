package com.yunfuyiren.AsymMaxInf.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wang
 *	图的定义
 *  邻接表
 *  注意：节点的id与下标差1，下标从0开始
 */
public class Graph {
	public ArrayList<ArrayList<Edge>> inEdges; //每个节点的入边邻接表
	public ArrayList<ArrayList<Edge>> outEdges; //每个节点的出边邻接表
	public ArrayList<Boolean> isActive;			//节点激活与否标记
	HashMap<String,Integer> nodeMapping;	//节点映射
	public int nodeNum;		//节点数;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Edge>>();
		outEdges=new ArrayList<ArrayList<Edge>>();
		nodeMapping=new HashMap<String,Integer>();
		isActive=new ArrayList<Boolean>();
	}
	/*文件格式必须为第一行是节点数，第二行开始为 srcnode desnode weight*/
	public void ReadFromFile(String FileName) throws IOException
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

			//节点阈值在各个Graph的子类中赋值	
			
			//节点激活与否的标记
			isActive.add(false);
		}
		while((data=br.readLine())!=null)
		{
			String[] d=data.split(" ");
			int src=Integer.valueOf(d[0]);
			int des=Integer.valueOf(d[1]);
			double val;
			if(d.length==3)
				val=Double.valueOf(d[2]);
			else
				val=1;
			//入边和出边List存的是相同的内存的数据，这里用的是引用。
			Edge edge=new Edge(src,des,val);
			inEdges.get(des-1).add(edge);
			outEdges.get(src-1).add(edge);			
		}
	}
}
