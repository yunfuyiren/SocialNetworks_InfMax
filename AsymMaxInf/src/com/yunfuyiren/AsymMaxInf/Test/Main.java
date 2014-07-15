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
		
	}
}

