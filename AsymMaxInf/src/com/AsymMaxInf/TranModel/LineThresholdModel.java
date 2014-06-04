package com.AsymMaxInf.TranModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.AsymMaxInf.entity.EdgeEntity;
import com.AsymMaxInf.entity.NodeEntity;

 /**
 * @author wangyang
 * ����ʵ����ֵģ�ʹ�������
 * �������������ͼ G(V,E)����ʼ��Ծ�ڵ㼯 Init 
 * ���ݽṹ���߼���HashMap<�ڵ��ţ��ڵ����>���ڵ㼯��HashMap<Դ�ڵ㣬Ŀ�Ľڵ㣬Ȩֵ>		
 * ���������������ڵ㼯������ڵ����
 */
public class LineThresholdModel 
 {
	HashMap<Integer,NodeEntity> nodes;    //�ڵ��ż����Ӧ�Ķ���
	
	ArrayList<Integer> R;		//��ǰ�Ѽ���ڵ㼯��
	int count;			//��ǰ��Ծ�ڵ㼯�ϵĴ�С
	public LineThresholdModel()
	{
		
	}
	
	//����Ϊ�����е�һ�����ߣ�����ģ�͵Ľڵ���ֵ���ͱ߹��׳�ʼ��
	public LineThresholdModel(LinkedList<EdgeEntity> e,HashMap<Integer,Integer> n)
	{
		this.nodes=new HashMap<Integer,NodeEntity>();
		//�ڵ㼯�Ľ���
		Iterator<Entry<Integer,Integer>> iter=n.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer,Integer> entry=(Entry<Integer,Integer>) iter.next();
			Integer key=entry.getKey();
			Integer val=entry.getValue();
			NodeEntity node=new NodeEntity(val);
			node.threshold=Math.random();  //��ֵ��ֵ
			nodes.put(key, node);	
		}
		
		//Ϊ�ڵ㼯�����ߺͳ��߼���
		for(EdgeEntity temp:e)
		{
			int src=temp.srcNode;
			int des=temp.desNode;
			NodeEntity node=nodes.get(src);
			double l=1/(node.degree);
			node.outEdges.put(des,l);
			
			node=nodes.get(des);
			node.inEdges.put(src, l);
		}
	}	
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>(Init);  //����Ծ�ڵ����R
		ArrayList<Integer> S=new ArrayList<Integer>();		//������ڵ㼯��
		//��ȡ��ʼ�ڵ�ķ�˿�ڵ㼯��
		for(Integer I:Init)
		{			
			NodeEntity node=nodes.get(I);
			Iterator<Entry<Integer,Double>> iter=node.outEdges.entrySet().iterator();
			Entry<Integer,Double> entry;
			while(iter.hasNext())
			{
				entry=(Entry<Integer,Double>) iter.next();
				Integer key=entry.getKey();
				S.add(key);
			}
		}
		while(true)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(Integer I:S)
			{
				NodeEntity node=nodes.get(I);
				Iterator<Entry<Integer,Double>> iter=
						node.inEdges.entrySet().iterator();
				Entry<Integer,Double> entry;
				int acum_val=0;  //��ÿ�������ٱߵ�Ȩֵ���
				while(iter.hasNext())
				{
					entry=(Entry<Integer,Double>) iter.next();
					Integer key=entry.getKey();
					if(R.contains(key))   //������ڽڵ��Ѽ���
						acum_val+=entry.getValue();
				}
				if(acum_val>=node.threshold)
				{
					temp.add(I);
					count++;
				}
			}
			if(temp.isEmpty())
				break;
			
			//�� temp �нڵ����R,ͬʱ��R�����нڵ��˿�ڵ����S
			R.addAll(temp);
			S.clear();
			while(!temp.isEmpty())
			{
				Integer I=temp.remove(0);
				NodeEntity node=nodes.get(I);
				Iterator<Entry<Integer,Double>> iter=node.outEdges.entrySet().iterator();
				while(iter.hasNext())
				{
					Entry<Integer,Double> entry=iter.next();
					int key=entry.getKey();
					
				}
			}		
		}
		return count;
	}
}
