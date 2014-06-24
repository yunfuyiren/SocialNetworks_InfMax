package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class Random_Algrithm extends MaxInf_Algrithm implements MaxInfAlgrithmInterface{

	public Random_Algrithm(Graph g,TransferModel tm,int k,int m)
	{
		TM=tm;
		G=g;
		K=k;
		M=m;
		S=new ArrayList<Integer>();
	}
	@Override
	public void Cac_MaxInf() {
		// TODO Auto-generated method stub
		Influence=0;
		Random r=new Random();
		int i=0;
		while(i<K)
		{
			int ran=r.nextInt(G.nodeNum);
			if(!S.contains(ran+1))
			{
				S.add(ran+1);
				i++;
			}
		}
		double res=0;
		for(int j=0;j<M;j++)
			res+=Propagation_Process(TM,S);
		Influence=res/M;
	}

	@Override
	public double Propagation_Process(TransferModel TM, ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		TM.SetThreshold();
		TM.SetWeight();
		return TM.Activiting(Init);
	}

}
