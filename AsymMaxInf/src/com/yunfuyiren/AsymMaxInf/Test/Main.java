package com.yunfuyiren.AsymMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.MaxDegreeHeuristic_Algrithm;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.Random_Algrithm;
import com.yunfuyiren.AsymMaxInf.MaxInfAlgthm.TopKGreedy_Algrithm;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.ICModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.LTModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.LTweakenModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.PRPModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelFactory.TransferModelFactory;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public class Main {
	public static void main(String[] args) throws IOException
	{
		ArrayList<Integer> Init =new ArrayList<Integer>();
		Init.add(2);
		String fileName="test.txt";
		Graph g=new Graph(fileName);
		
//		TopKGreedy_Algrithm top;		
		//LT模型			
		TransferModelFactory ltfm=new LTModelFactory();
		TransferModel ltmodel=ltfm.CreatingModel(g);
//		top=new TopKGreedy_Algrithm(g,ltmodel,2,2);
		//IC模型
//		TransferModelFactory icfm=new ICModelFactory();
//		TransferModel icmodel=icfm.CreatingModel(g);
//		top=new TopKGreedy_Algrithm(g,icmodel,2,2);
		//PageRank模型
//		TransferModelFactory prpfm=new PRPModelFactory();
//		TransferModel prpmodel=prpfm.CreatingModel(g);
//		top=new TopKGreedy_Algrithm(g,prpmodel,2,2);
		//LT衰减模型
//		TransferModelFactory ltwfm=new LTweakenModelFactory();
//		TransferModel ltwmodel=ltwfm.CreatingModel(g);
//		top=new TopKGreedy_Algrithm(g,ltwmodel,2,1000);
//		
//		top.Cac_MaxInf();
//		System.out.println(top.S);
//		System.out.println(top.Influence);
		
		/*heuristics 启发式算法*/
//		MaxDegreeHeuristic_Algrithm MaxD=new MaxDegreeHeuristic_Algrithm(g,ltwmodel,2,2);
//		MaxD.Cac_MaxInf();
//		System.out.println(MaxD.S);
//		System.out.println(MaxD.Influence);
		
		/*random 随机种子节点*/
		Random_Algrithm ranAlgthm=new Random_Algrithm(g,ltmodel,2,2);
		ranAlgthm.Cac_MaxInf();
		System.out.println(ranAlgthm.S);
		System.out.println(ranAlgthm.Influence);
	}
}
