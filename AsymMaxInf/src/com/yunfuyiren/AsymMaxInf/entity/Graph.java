package com.yunfuyiren.AsymMaxInf.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wang
 *	ͼ�Ķ���
 *  �ڽӱ�
 *  ע�⣺�ڵ��id���±��1���±��0��ʼ
 */
public class Graph {
	public ArrayList<ArrayList<Edge>> inEdges; //ÿ���ڵ������ڽӱ�
	public ArrayList<ArrayList<Edge>> outEdges; //ÿ���ڵ�ĳ����ڽӱ�
	public ArrayList<Boolean> isActive;			//�ڵ㼤�������
	HashMap<String,Integer> nodeMapping;	//�ڵ�ӳ��
	public int nodeNum;		//�ڵ���;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Edge>>();
		outEdges=new ArrayList<ArrayList<Edge>>();
		nodeMapping=new HashMap<String,Integer>();
		isActive=new ArrayList<Boolean>();
	}
	/*�ļ���ʽ����Ϊ��һ���ǽڵ������ڶ��п�ʼΪ srcnode desnode weight*/
	public void ReadFromFile(String FileName) throws IOException
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

			//�ڵ���ֵ�ڸ���Graph�������и�ֵ	
			
			//�ڵ㼤�����ı��
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
			//��ߺͳ���List�������ͬ���ڴ�����ݣ������õ������á�
			Edge edge=new Edge(src,des,val);
			inEdges.get(des-1).add(edge);
			outEdges.get(src-1).add(edge);			
		}
	}
}
