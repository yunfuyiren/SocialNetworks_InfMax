package com.yunfuyiren.AsymMaxInf.MaxInfAlgthm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.CELF_LTModel;
//delta边际收益的类型
enum Type{
	UC,CB
}
//placement objectives 目标函数指标
enum RType{
	DL,DT,PA
}
/**
 * @author wang
 * 《Cost-effective Outbreak Detection in Networks》中，提出的CELF算法
 * 算法的输入：图G，在这里t表示为边权），cost每个节点的成本，B总成本，Init为开始产生级联的节点，类似于事件发生
 * 算法的输出：放置监视器的集合，惩罚减少的PA/DL/DT的值集合
 */
public class CELF_Algrithm{
	//输入参数
	Graph G;
	double B;	
	CELF_LTModel TM;   //传播模型
	ArrayList<Double> Cost;	//在节点上设置监视器的花费成本列表
	ArrayList<Integer> Init;		//初始发生级联故障的节点
	 
	RType rtype;		//目标函数的指标类型
	//需要的参数 
	//输出结果
	 ArrayList<Integer> ResA;	//放置监视器的集合，
	double PA,PD,DT;
	public CELF_Algrithm(Graph g, CELF_LTModel tm,ArrayList<Double> cost,double B, ArrayList<Integer> Init)
	{
		G=g;
		this.B=B;
		Cost=cost;
		TM=tm;
		this.Init=Init;
		
		ResA=new ArrayList<Integer>();
	}

	public ArrayList<Integer> Cac_MaxInf(RType type) {
		// TODO Auto-generated method stub
		rtype=type;
		ArrayList<Integer> Auc=new ArrayList<Integer>();
		ArrayList<Integer> Acb=new ArrayList<Integer>();
		Auc=LazyForward(Type.UC);
		Acb=LazyForward(Type.CB);
		return Calc_R(Auc)>Calc_R(Acb)?Auc:Acb;
	}
	
	private class deltaClass{
		int num;
		double delta;
		deltaClass(int i){
			num=i;
		}
	}
	
	private ArrayList<Integer> LazyForward(Type type) {
		// TODO Auto-generated method stub
		ArrayList<Integer> A=new ArrayList<Integer>();	//A集合为结果元素集合
		LinkedList<Integer> VoutA=new LinkedList<Integer>();  //V\A集合满足A以外的同时Csum(A+s)<B的集合；
		LinkedList<deltaClass> deltaclass=new LinkedList<deltaClass>();
		//比较器的定义
		Comparator<deltaClass> cmpr=new Comparator<deltaClass>(){
			@Override
			public int compare(deltaClass o1, deltaClass o2) {
				// TODO Auto-generated method stub
				return o1.delta>o2.delta?1:(o1.delta==o2.delta?0:-1);
			}
		};
		for(int i=0;i<G.nodeNum;i++)
		{
			VoutA.add(i+1);
			deltaClass temp=new deltaClass(i+1);		
			if(type==Type.UC)
				temp.delta=Calc_R(new ArrayList<Integer>(temp.num));
			else
				temp.delta=Calc_R(new ArrayList<Integer>(temp.num))/Cost.get(temp.num-1);
			deltaclass.add(temp);
		}
		Collections.sort(deltaclass, cmpr);
		/*找寻V/A中满足c(A+S)<B的节点集合*/
		NotExceedValue(A,VoutA);
		while(!VoutA.isEmpty())
		{
			HashMap<Integer, Boolean> curs=new HashMap<Integer,Boolean>();
			for(Integer stemp:VoutA)
			{
				curs.put(stemp-1, false);				
			}
			while(true)
			{
				deltaClass top=deltaclass.poll();
				if(type==Type.UC)
				{
					if(curs.get(top.num-1)==false) //curs对应的为false
					{
						double Ra=Calc_R(A);
						A.add(top.num);
						double Ras=Calc_R(A);
						A.remove(A.size()-1);
						curs.put(top.num-1, true);
						top.delta=Ras-Ra;
						InsertLinkList(deltaclass,top);
					}
					else{
						A.add(top.num);
						break;
					}
				}
				else
				{
					if(curs.get(top.num-1)==false) //curs对应的为false
					{
						double Ra=Calc_R(A);
						A.add(top.num);
						double Ras=Calc_R(A);
						A.remove(A.size()-1);
						curs.put(top.num-1, true);
						top.delta=(Ras-Ra)/Cost.get(top.num-1);
						InsertLinkList(deltaclass,top);
					}
					else{
						A.add(top.num);
						break;
					}
				}
			}
			NotExceedValue(A,VoutA);
		}
		return A;
	}
	private void InsertLinkList(LinkedList<deltaClass> deltaclass,
			deltaClass top) {
		// TODO Auto-generated method stub
		int index=0;
		int i;
		for(i=0;i<deltaclass.size();i++)
		{
			deltaClass temp=deltaclass.get(i);
			if(temp.delta<top.delta)
			{
				index=i+1;
			}
		}
		deltaclass.add(index, top);
	}

	//计算惩罚递减函数,在此函数中用到传播模型
	private double Calc_R(ArrayList<Integer> A)
	{
		//计算PA
		TM.SetThreshold();
		TM.SetWeight();
		TM.Activiting(A,Init);
		if(rtype==RType.DL)
			return TM.DL;
		else if(rtype==RType.DT)
			return TM.DT;
		else
			return TM.PA; 
	}
	
	//从vout取出一个满足C(s+A)<B的节点
	private void NotExceedValue(ArrayList<Integer> A,LinkedList<Integer> VoutA) {
		// TODO Auto-generated method stub
		double sumc=0;
		for(Integer i:A)
		{
			sumc+=Cost.get(i-1);
		}
		for(int i=0;i<VoutA.size();i++)
		{
			if(B<sumc+Cost.get(i))
			{
				VoutA.remove(i);
			}
		}
	}
	public static void main(String[] args)
	{
		LinkedList<deltaClass> deltaclass=new LinkedList<deltaClass>();
		//比较器的定义
		Comparator<deltaClass> cmpr=new Comparator<deltaClass>(){
			@Override
			public int compare(deltaClass o1, deltaClass o2) {
				// TODO Auto-generated method stub
				return o1.delta>o2.delta?1:(o1.delta==o2.delta?0:-1);
			}
		};

		Collections.sort(deltaclass, cmpr);		
//		System.out.println(VoutA);
	}
}
