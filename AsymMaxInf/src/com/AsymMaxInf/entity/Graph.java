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
	ArrayList<ArrayList<Integer>> inEdges; //ÿ���ڵ������ڽӱ�
	ArrayList<ArrayList<Integer>> outEdges; //ÿ���ڵ�ĳ����ڽӱ�
	ArrayList<ArrayList<Double>>	inEdgesWeight; //ÿ���ڵ������ڽӱ�Ȩ�أ�
	ArrayList<ArrayList<Double>>	outEdgesWeight;//ÿ���ڵ�ĳ����ڽӱ�Ȩ�ء�
	HashMap<String,Integer> nodeMapping;	//�ڵ�ӳ��
	int nodeNum;		//�ڵ���;
	public Graph()
	{
		inEdges=new ArrayList<ArrayList<Integer>>();
		outEdges=new ArrayList<ArrayList<Integer>>();
		inEdgesWeight=new ArrayList<ArrayList<Double>>();
		outEdgesWeight=new ArrayList<ArrayList<Double>>();
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
			//�ڵ���ֵ�ڸ���ͼ�������и�ֵ
			
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
