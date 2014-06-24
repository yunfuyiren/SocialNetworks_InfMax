package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class TopKGreedy_Algrithm extends MaxInf_Algrithm implements MaxInfAlgrithmInterface{
	
	/*g为图，tm为传播模型，k为种子大小，m为重复次数*/
	public TopKGreedy_Algrithm(Graph g,TransferModel tm,int k,int m){
		G=g;	
		K=k;
		TM=tm;
		this.M=m;
		S=new ArrayList<Integer>();
	}
	//影响力最大化算法
	private void MaxInf_Algrithm()
	{	
		Influence=0;
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
						double rs0=Propagation_Process(TM,S);
						double rs1=Propagation_Process(TM,SS);
						if((rs1-rs0)>7)
							System.out.println(rs1-rs0);
	
						sv+=(rs1-rs0);/*结点v在所有时刻的边际效益总和*/
						if(sv/M>7)
							System.out.println(rs1-rs0);
					}
					sv=sv/M; //节点的平均边际效益
					maxMB.put(j+1, sv);		//影响力之差，边界影响力
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
		}	
	}
	
	/*得到最终的目标初始集合S后，计算其影响力。多次计算求平均*/
	@Override
	public void Cac_MaxInf()
	{
		 MaxInf_Algrithm();
		double res=0;
		for(int i=0;i<M;i++)
			res+=Propagation_Process(TM,S);
		Influence=res/M;
	}
	/*工厂模式，传播模型的传播过程函数，返回影响的节点数*/
	@Override
	public double Propagation_Process(TransferModel TM,ArrayList<Integer> Init){
		TM.SetThreshold();
		TM.SetWeight();
		return TM.Activiting(Init);
	}
}
