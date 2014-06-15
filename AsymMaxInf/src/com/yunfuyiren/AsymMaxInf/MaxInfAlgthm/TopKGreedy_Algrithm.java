package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.SetGraphAttributesInterface;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class TopKGreedy_Algrithm {
	Graph G;			//����ͼ
	TransferModel TM;   //����ģ��
	public int M;		//�ظ��������
	int K;		//���Ӽ��ϴ�С,Top K��K
	
	/*�������*/
	public ArrayList<Integer> S;	//������Ӽ���
	public double Influence;    //���Ӱ������С
	
	/*gΪͼ��gaΪͼ�ļӹ��ӿڣ�maΪģ�͵ļӹ��ӿڣ�kΪ���Ӵ�С��MΪ�ظ�����*/
	public TopKGreedy_Algrithm(Graph g,TransferModel tm,int k,int m){
		G=g;	
		K=k;
		TM=tm;
		this.M=m;
		S=new ArrayList<Integer>();
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
						int rs0=Propagation_Process(TM,S);
						int rs1=Propagation_Process(TM,SS);
						sv+=(double)(rs1-rs0);/*���v������ʱ�̵ı߼�Ч���ܺ�*/
					}
					sv=sv/M; //�ڵ��ƽ���߼�Ч��
					maxMB.put(j+1, sv);
					SS.clear();
					SS=null;
				}
			}
			double tempmax=0;
			int tempindex=1;
			Iterator<Entry<Integer, Double>> iter=maxMB.entrySet().iterator();
			if(iter.hasNext())
			{
				Map.Entry<Integer, Double> entry=
						(Map.Entry<Integer, Double>)iter.next();
				tempindex=entry.getKey();
				tempmax=entry.getValue();
			}	
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
			S.add(tempindex);						//������ı߽�ڵ����Ŀ�꼯����
			Influence+=maxMB.get(tempindex);		//Ӱ�����ı���
		}
	}
	
	/*����ģʽ������ģ�͵Ĵ������̺���������Ӱ��Ľڵ���*/
	public int Propagation_Process(TransferModel TM,ArrayList<Integer> Init){
		return TM.Activiting(G,Init);
	}
	
	/*����ģʽ����ͼ�����Խ�ģ*/
	public void Building_Graph(SetGraphAttributesInterface db){
		db.SetThreshold();
		db.SetWeight();
	}
}
