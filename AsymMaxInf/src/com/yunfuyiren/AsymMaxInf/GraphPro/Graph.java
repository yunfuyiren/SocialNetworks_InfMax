package com.yunfuyiren.AsymMaxInf.GraphPro;

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
 *  �̳�ͼ�������ýӿڣ�Ϊ��ͬ��ͼ���ò�ͬ������
 */
public class Graph {
	public ArrayList<ArrayList<Edge>> inEdges; //ÿ���ڵ������ڽӱ�
	public ArrayList<ArrayList<Edge>> outEdges; //ÿ���ڵ�ĳ����ڽӱ�
	public ArrayList<Boolean> isActive;			//�ڵ㼤�������
	HashMap<String,Integer> nodeMapping;	//�ڵ�ӳ��
	public ArrayList<Double>	nodeThreshold;	//�ڵ���ֵ
	public ArrayList<Double>	nodeInfluenceWeight;	//�ڵ�Ӱ����Ȩֵ����ͬ�ڵ㼤����������Ӱ������ͬ�������������յĽڵ����
	public int nodeNum;		//�ڵ���;
	
	public Graph(String FileName) throws IOException
	{
		System.out.println("Graph���캯��");
		inEdges=new ArrayList<ArrayList<Edge>>();
		outEdges=new ArrayList<ArrayList<Edge>>();
		nodeMapping=new HashMap<String,Integer>();
		isActive=new ArrayList<Boolean>();
		nodeThreshold=new ArrayList<Double>();
		nodeInfluenceWeight=new ArrayList<Double>();
		ReadFromFile(FileName);
		SetDefaultInfluceWeight();
	}
	public Graph()
	{
		
	}
	/*�ļ���ʽ����Ϊ��һ���ǽڵ������ڶ��п�ʼΪ srcnode desnode weight*/
	private void ReadFromFile(String FileName) throws IOException
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
	
	/*�������б��ʼ��*/
	public void InitIsActive()
	{
		for(int i=0;i<isActive.size();i++)
		{
			isActive.set(i, false);
		}
	}
	/*���ýڵ�Ӱ����Ȩֵ��ÿ���ڵ��Ӱ���������𣬶���������Ӱ��Ľڵ����*/
	public void SetInfluceWeight()
	{
		
	}	
	/*���ýڵ�Ĭ�ϵ�Ӱ����ȨֵΪ1*/
	private void SetDefaultInfluceWeight()
	{
		for(int i=0;i<nodeNum;i++)
		{
			nodeInfluenceWeight.add((double) 1);
		}
	}
}
