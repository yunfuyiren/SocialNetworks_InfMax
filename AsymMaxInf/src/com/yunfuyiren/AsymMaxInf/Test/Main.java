package com.yunfuyiren.AsymMaxInf.Test;

import java.io.IOException;
import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphFactory.GraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.ICGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.LTGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphFactory.PRPGraphFactory;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.ICGraph;
import com.yunfuyiren.AsymMaxInf.GraphPro.LTGraph;
import com.yunfuyiren.AsymMaxInf.GraphPro.PRPGraph;
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
		TopKGreedy_Algrithm top;
		
		//线性阈值模型	
		GraphFactory ltfactory=new LTGraphFactory();
		Graph ltg=ltfactory.CreateGraph(fileName);
//		TransferModelFactory ltfm=new LTModelFactory();
//		TransferModel ltmodel=ltfm.CreatingModel();
//		top=new TopKGreedy_Algrithm(ltg,ltmodel,2,2);
		//级联模型	
//		GraphFactory icfactory=new ICGraphFactory();
//		Graph icg=icfactory.CreateGraph(fileName);
//		TransferModelFactory icfm=new ICModelFactory();
//		TransferModel icmodel=icfm.CreatingModel();
//		top=new TopKGreedy_Algrithm(icg,icmodel,2,2);
//		//PageRank模型
//		GraphFactory prpfactory=new PRPGraphFactory();
//		Graph prpg=prpfactory.CreateGraph(fileName);
//		TransferModelFactory prpfm=new PRPModelFactory();
//		TransferModel prpmodel=prpfm.CreatingModel();
//		top=new TopKGreedy_Algrithm(prpg,prpmodel,2,2);
		//衰减线性阈值模型
		TransferModelFactory ltwfm=new LTweakenModelFactory();
		TransferModel ltwmodel=ltwfm.CreatingModel();
		top=new TopKGreedy_Algrithm(ltg,ltwmodel,2,2);
		
		top.Greedy_Algrithm();
		System.out.println(top.S);
		System.out.println(top.Influence);
//		MaxDegreeHeuristic MaxD=new MaxDegreeHeuristic(fileName);
//		ArrayList<Integer> R=MaxD.MaxDegreeList(3);

	}
}
