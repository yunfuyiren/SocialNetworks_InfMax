package com.yunfuyiren.AsymMaxInf.TranModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.yunfuyiren.AsyMaxInf.Interface.SetModelAttributes;
import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.PRPGraph;

public class PageRankPModel extends TransferModel implements SetModelAttributes{
	PRPGraph G;
	
	/*����Ϊһ��PRPGraph*/
	public PageRankPModel(PRPGraph graph)
	{
		G=graph;
		G.SetPageRank1();
		System.out.println(G.pageRank);
		G.SetWeight();
	}
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>(Init);		   //����Ծ�ڵ����R
		Set<Integer>S=new HashSet<Integer>();  //������ڵ㼯��,����ʹ��Set��Ϊ�˷�ֹ������ͬ�ڵ�
		count=0;  //����ڵ�������
		G.InitIsActive();
		//��ȡ��ʼ�ڵ�ķ�˿�ڵ�ļ���
		for(Integer I:Init)
		{
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode))		//����÷�˿�ڵ���δ��������������Ķ���
				{
					S.add(edge.desNode);
				}
			}	
		}
		Random r=new Random();
		while(true) //ÿ��ѭ������һ��ʱ�䲽
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			ArrayList<Double> pActive=new ArrayList<Double>(); 
			//����������У�ÿ��ѭ��ʱ����������S�����еĴ�����ڵ�ļ�����ʣ�ȫ��������ٽ��м������
			//������������������ڵ�֮���л�����Ҫ��ȷ��һ���ڵ��״̬�������������Ĺ���
			for(Integer I:S)
			{
				double p=0;
				//����ڵ㱻����ĸ���
				for(Edge e:G.inEdges.get(I-1))
				{
					if(G.isActive.get(e.srcNode-1))
					{
						p+=e.edgeWeight;
					}
				}
				pActive.add(p);
			}
			int i=0;  //Ϊ�˱�����forѭ��ʱ��pActive��G.isActive��Ӧ
			for(Integer I:S)
			{
				double ran=r.nextDouble();
				if(ran<pActive.get(i))
				{
					G.isActive.set(I-1,true);
					temp.add(I);
					count++;
				}
				i++;
			}
			
			//û�нڵ㱻���Ҳ��û����Ϣ���ݣ�Ӱ����ֹͣ����
			if(temp.isEmpty())
				break;
			
			//�� temp �нڵ����R,ͬʱ��R�����нڵ��˿�ڵ����S
			R.addAll(temp);
			S.clear();
			for(Integer I:temp)
			{
				for(Edge e:G.outEdges.get(I-1))
					if(G.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
			}
			temp=null;
		}		
		return count;
	}
	
}
