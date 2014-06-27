package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.SetModelAttributesInterface;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

enum Type{
	UC,CB
}
/**
 * @author wang
 * 《Cost-effective Outbreak Detection in Networks》中，提出的CELF算法
 * 算法的输入：图G，在这里t表示为边权），cost每个节点的成本，B总成本，Init为开始产生级联的节点，类似于事件发生
 */
public class CELF_Algrithm{
	//输入参数
	Graph G;
	double B;			
	ArrayList<Double> Cost;	//节点设置监视器的花费列表
	 ArrayList<Integer> Init;		//初始发生级联的节点
	//需要的参数
	public CELF_Algrithm(Graph g, ArrayList<Double> cost,double B, ArrayList<Integer> Init)
	{
		G=g;
		this.B=B;
		Cost=cost;
		this.Init=Init;
	}

	public ArrayList<Integer> Cac_MaxInf() {
		// TODO Auto-generated method stub
		ArrayList<Integer> Auc=new ArrayList<Integer>();
		ArrayList<Integer> Acb=new ArrayList<Integer>();
		Auc=LazyForward(Type.UC);
		Acb=LazyForward(Type.CB);
		return Calc_R(Auc)>Calc_R(Acb)?Auc:Acb;
	}
	
	public ArrayList<Integer> LazyForward(Type type) {
		// TODO Auto-generated method stub
		ArrayList<Integer> A=new ArrayList<Integer>();
		ArrayList<Integer> VoutA=new ArrayList<Integer>();  //V\A集合外的元素；
		ArrayList<Double> delta=new ArrayList<Double>();	//记录节点边界收益
		for(int i=0;i<G.nodeNum;i++)
		{
			delta.add(Double.MAX_VALUE);
			VoutA.add(i+1);
		}
		while(!VoutA.isEmpty())
		{
			/*找寻c(A+S)<B的节点*/
			int s=IsExceedValue(A,VoutA);
			/*没找到满足条件的节点*/
			if(s==-1)
				return A;
			HashMap<Integer, Boolean> curs=new HashMap<Integer,Boolean>();
			for(Integer stemp:VoutA)
			{
				curs.put(stemp, false);
			}
			while(true)
			{
				if(type==Type.UC)
				{
					double Ra=Calc_R(A);
					A.add(s);
					double Ras=Calc_R(A);
					A.remove(A.size()-1);
				}else{
					
				}
				
				if 
			}
		}
		return A;
	}
	//计算惩罚递减函数
	private double Calc_R(ArrayList<Integer> A)
	{
		return 0;
	}
	private int IsExceedValue(ArrayList<Integer> a, ArrayList<Integer> voutA) {
		// TODO Auto-generated method stub
		return 0;
	}
}
