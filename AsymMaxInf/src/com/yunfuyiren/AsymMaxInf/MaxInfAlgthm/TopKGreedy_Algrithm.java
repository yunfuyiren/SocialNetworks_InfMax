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
	Graph G;			//网络图
	TransferModel TM;   //传播模型
	public int M;		//重复计算次数
	int K;		//种子集合大小,Top K的K
	
	/*输出集合*/
	public ArrayList<Integer> S;	//输出种子集合
	public double Influence;    //输出影响力大小
	
	/*g为图，ga为图的加工接口，ma为模型的加工接口，k为种子大小，M为重复次数*/
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
			
			//对每一个节点
			for(int j=0;j<G.inEdges.size();j++)
			{
				if(!S.contains(j+1))
				{
					ArrayList<Integer> SS=new ArrayList<Integer>(S);
					SS.add(j+1);
					double sv=0;
					for(int t=0;t<M;t++)  //计算t时刻的节点边际效益
					{
						int rs0=Propagation_Process(TM,S);
						int rs1=Propagation_Process(TM,SS);
						sv+=(double)(rs1-rs0);/*结点v在所有时刻的边际效益总和*/
					}
					sv=sv/M; //节点的平均边际效益
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
			S.add(tempindex);						//将计算的边界节点加入目标集合中
			Influence+=maxMB.get(tempindex);		//影响力的保留
		}
	}
	
	/*工厂模式，传播模型的传播过程函数，返回影响的节点数*/
	public int Propagation_Process(TransferModel TM,ArrayList<Integer> Init){
		return TM.Activiting(G,Init);
	}
	
	/*工厂模式，对图的属性建模*/
	public void Building_Graph(SetGraphAttributesInterface db){
		db.SetThreshold();
		db.SetWeight();
	}
}
