package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

public class LTweakenModel extends TransferModel implements SetModelAttributesInterface{
	double r_param;  //传播过程中的衰减参数
	public LTweakenModel()
	{
		r_param=0.8;
	}
	@Override
	public int Activiting(Graph g, ArrayList<Integer> Init) {
		// TODO 自动生成的方法存根		
		g.InitIsActive();
		count=Init.size();  //初始活跃节点本身也算
		R=new ArrayList<Integer>(Init);  //将活跃节点加入R
		Set<Integer> S=new HashSet<Integer>();		//待激活节点集合,这里使用Set是为了防止存入相同节点
		//获取初始节点的粉丝节点集合
		for(Integer I:Init)
		{
			g.isActive.set(I-1, true);
			ArrayList<Edge> edges=g.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode)) //如果邻居节点是非活跃的，加入待激活的队列
				{
					S.add(edge.desNode);
				}
			}
		}
		ArrayList<Integer> R_cur=new ArrayList<Integer>(Init);	//当前轮的活跃节点集合
		/*激活过程*/
		while(true)  //每次循环就是一个时间步
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			//对于S中的每一个节点
			for(Integer I:S)
			{
				double threshold=g.nodeThreshold.get(I-1);
				ArrayList<Edge> edges=g.inEdges.get(I-1);
				double imfSum=0,imfSumcur=0; //边权影响力之和、上一时间t边权影响力之和
				for(Edge edge:edges)
				{
					if(R_cur.contains(edge.srcNode))
						imfSumcur+=edge.edgeWeight;
					else if(g.isActive.get(edge.srcNode-1))
						imfSum+=edge.edgeWeight;
				}
				imfSum=imfSum*r_param+imfSumcur;  //历史节点对当前节点的活跃度影响有衰减
				if(imfSum>=threshold)
				{
					temp.add(I);
					g.isActive.set(I-1, true);
					count++;
				}
			}
			if(temp.isEmpty())
				break;
			
			//将 temp 中节点加入R,同时将R中所有节点粉丝节点放入S
			R.addAll(temp);
			S.clear();
			R_cur.clear();
			for(Integer I:temp)
			{
				ArrayList<Edge> edges=g.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(g.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
				}
			}
			temp=null;
		}
		return count;
	}
	
}
