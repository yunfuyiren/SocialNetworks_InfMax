package com.AsymMaxInf.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wang
 *	ͼ�Ķ���
 *  �ڽӱ�
 */
public class Graph {
	ArrayList<ArrayList<Edge>> inEdges; //ÿ���ڵ������ڽӱ�
	ArrayList<ArrayList<Edge>> outEdges; //ÿ���ڵ�ĳ����ڽӱ�
	
	HashMap<String,Integer> nodeMapping;	//�ڵ�ӳ��
	int nodeNum;		//�ڵ���;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Edge>>();
		outEdges=new ArrayList<ArrayList<Edge>>();
		nodeMapping=new HashMap<String,Integer>();
	}
	/*�ļ���ʽ����Ϊ��һ���ǽڵ������ڶ��п�ʼΪ srcnode desnode weight*/
	public void ReadFromeFile(String FileName) throws IOException
	{
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new FileReader(FileName));
		String data=br.readLine();
		nodeNum=Integer.parseInt(data);
		//��ýڵ����󣬶������ʼ��
		for(int i=0;i<nodeNum;i++)
		{
			ArrayList<Edge> elem=new ArrayList<Edge>();
			inEdges.add(elem);
			elem=new ArrayList<Edge>();
			outEdges.add(elem);

			//�ڵ���ֵ�ڸ���ͼ�������и�ֵ			
		}
		while((data=br.readLine())!=null)
		{
			String[] d=data.split(" ");
			int src=Integer.valueOf(d[0]);
			int des=Integer.valueOf(d[1]);
			double val=Double.valueOf(d[2]);
			//��ߺͳ���List�������ͬ���ڴ�����ݣ������õ������á�
			Edge edge=new Edge(src,des,val);
			inEdges.get(des).add(edge);
			outEdges.get(src).add(edge);			
		}
	}
}
