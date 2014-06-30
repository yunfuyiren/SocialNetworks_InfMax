package com.yunfuyiren.AsymMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.MaxDegreeHeuristic_Algrithm;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.Random_Algrithm;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.TopKGreedy_Algrithm;
import com.yunfuyiren.AsymMaxInf.TranModelPro.ICModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.LTModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.LTweakenModel;
import com.yunfuyiren.AsymMaxInf.TranModelPro.PRPModel;
public class Main {
	public static void main(String[] args) throws IOException
	{
		ArrayList<Integer> Init =new ArrayList<Integer>();
		Init.add(2);
		String fileName="test.txt";
		Graph g=new Graph(fileName);
		
//		TopKGreedy_Algrithm top;		
//		//LT模型			
//		top=new TopKGreedy_Algrithm(g,LTModel.factory.CreatingModel(g),2,2);
//		//IC模型
//		top=new TopKGreedy_Algrithm(g,ICModel.factory.CreatingModel(g),2,2);
//		//PageRank模型
//		top=new TopKGreedy_Algrithm(g,PRPModel.factory.CreatingModel(g),2,2);
//		//LT衰减模型
//		top=new TopKGreedy_Algrithm(g,LTweakenModel.factory.CreatingModel(g),2,2);
//		
//		top.Cac_MaxInf();
//		System.out.println(top.S);
//		System.out.println(top.Influence);
//		
//		/*heuristics 启发式算法*/
//		MaxDegreeHeuristic_Algrithm MaxD=new MaxDegreeHeuristic_Algrithm(g,ICModel.factory.CreatingModel(g),2,2);
//		MaxD.Cac_MaxInf();
//		System.out.println(MaxD.S);
//		System.out.println(MaxD.Influence);
//		
//		/*random 随机种子节点*/
//		Random_Algrithm ranAlgthm=new Random_Algrithm(g,ICModel.factory.CreatingModel(g),2,2);
//		ranAlgthm.Cac_MaxInf();
//		System.out.println(ranAlgthm.S);
//		System.out.println(ranAlgthm.Influence);
		
		LinkedList<deltaClass> deltaclass=new LinkedList<deltaClass>();
		//比较器的定义
		Comparator<deltaClass> cmpr=new Comparator<deltaClass>(){
			@Override
			public int compare(deltaClass o1, deltaClass o2) {
				// TODO Auto-generated method stub
				return o1.delta>o2.delta?1:(o1.delta==o2.delta?0:-1);
			}
		};
//		Collections.sort(deltaclass, cmpr);
		Random rand=new Random(46);
		for(int i=0;i<10;i++)
		{
			deltaClass dc=new deltaClass(i);
			dc.num=i+1;
			dc.delta=rand.nextDouble();
			deltaclass.add(dc);
		}
		Collections.sort(deltaclass, cmpr);
		
		deltaClass dc=new deltaClass(11);
		dc.delta=0.5;
		InsertLinkList(deltaclass,dc);
		for(int i=0;i<deltaclass.size();i++)
			System.out.println("num= "+deltaclass.get(i).num+" delta="+deltaclass.get(i).delta);
	}
	@SuppressWarnings("unused")
	private static void InsertLinkList(LinkedList<deltaClass> deltaclass,
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
}
class deltaClass{
	public int num;
	public double delta;
	deltaClass(int i){
		num=i;
	}
}

