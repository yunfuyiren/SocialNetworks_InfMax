package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yunfuyiren.AsymMaxInf.TranModel.PageRankPModel;
import com.yunfuyiren.AsymMaxInf.entity.PRPGraph;

public class TopKGreedy_Algrithm {
	PRPGraph G;
	public final int M=2;		//�ظ��������
	public ArrayList<Integer> S;	//������Ӽ���
	int K;		//���Ӽ��ϴ�С
	PageRankPModel PRPModel;
	public TopKGreedy_Algrithm(PRPGraph g,int k){
		G=g;	
		S=new ArrayList<Integer>();
		K=k;
		PRPModel=new PageRankPModel(G);	//��ʼ������ģ��
	}
	public void Greedy_Algrithm()
	{
		
		for(int i=0;i<K;i++)
		{
			HashMap<Integer,Double> maxMB=new HashMap<Integer,Double>();
			
			//��ÿһ���ڵ�
			for(int j=0;j<G.inEdges.size();j++)
			{
				if(!S.contains(j+1))
				{
					ArrayList<Integer> SS=new ArrayList<Integer>(S);
					SS.add(j+1);
					double sv=0;
					for(int t=0;t<M;t++)  //����tʱ�̵Ľڵ�߼�Ч��
					{
						int rs0=PRPModel.Activiting(S);
						int rs1=PRPModel.Activiting(SS);
						sv+=(double)(rs1-rs0);/*���v������ʱ�̵ı߼�Ч���ܺ�*/
					}
					sv=sv/M; //�ڵ��ƽ���߼�Ч��
					maxMB.put(j+1, sv);
					SS.clear();
					SS=null;
				}
			}
			double tempmax=0;
			int tempindex=0;
			Iterator<Entry<Integer, Double>> iter=maxMB.entrySet().iterator();
			while(iter.hasNext())
			{
				Map.Entry<Integer, Double> entry=
						(Map.Entry<Integer, Double>)iter.next();
				if(tempmax<entry.getValue())
				{
					tempindex=entry.getKey();
					tempmax=entry.getValue();
				}
			}
			S.add(tempindex);
		}
	}

//	public 
}
