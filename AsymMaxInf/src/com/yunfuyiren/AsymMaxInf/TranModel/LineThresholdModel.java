package com.yunfuyiren.AsymMaxInf.TranModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.yunfuyiren.AsyMaxInf.Interface.SetModelAttributes;
import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.LTGraph;

 /**
 * @author wangyang
 * ����ʵ����ֵģ�ʹ�������
 * �������������ͼ G(V,E)����ʼ��Ծ�ڵ㼯(�ڵ���List) Init 	
 * ���������������ڵ㼯������ڵ����
 */
public class LineThresholdModel extends TransferModel implements SetModelAttributes
{
	LTGraph G;    //�ڵ��ż����Ӧ�Ķ���	
		
	/*����Ϊһ��LTGraph*/
	public LineThresholdModel(LTGraph graph)
	{
		G=graph;
		G.SetThreshold();
		G.SetWeight();
	}
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>(Init);  //����Ծ�ڵ����R
		Set<Integer> S=new HashSet<Integer>();		//������ڵ㼯��,����ʹ��Set��Ϊ�˷�ֹ������ͬ�ڵ�
		//��ȡ��ʼ�ڵ�ķ�˿�ڵ㼯��
		for(Integer I:Init)
		{			
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode)) //����ھӽڵ��Ƿǻ�Ծ�ģ����������Ķ���
				{
					S.add(edge.desNode);
				}
			}	
		}
		while(true)  //ÿ��ѭ������һ��ʱ�䲽
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			//����S�е�ÿһ���ڵ�
			for(Integer I:S)
			{
				double threshold=G.nodeThreshold.get(I-1);
				ArrayList<Edge> edges=G.inEdges.get(I-1);
				double imfSum=0;		//��ȨӰ����֮��
				for(Edge edge:edges)
				{
					if(G.isActive.get(edge.srcNode-1))
						imfSum+=edge.edgeWeight;
				}
			
				if(imfSum>=threshold)
				{
					temp.add(I);
					G.isActive.set(I-1, true);
					count++;
				}
			}
			if(temp.isEmpty())
				break;
			
			//�� temp �нڵ����R,ͬʱ��R�����нڵ��˿�ڵ����S
			
			R.addAll(temp);
			S.clear();
			for(Integer I:temp)
			{
				ArrayList<Edge> edges=G.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(G.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
				}
			}	
			temp=null;
		}
		System.out.println(G.nodeThreshold);
		return count;
	}
}
