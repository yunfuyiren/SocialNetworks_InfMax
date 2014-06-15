package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
 /**
 * @author wangyang
 * 该类实现阈值模型传播过程
 * 输入参数：网络图 G(V,E)，初始活跃节点集(节点编号List) Init 	
 * 输出参赛：被激活节点集，激活节点个数
 */
public class LTModel extends TransferModel  implements SetModelAttributesInterface
{		
	/*输入为一个LTGraph*/
	public LTModel()
	{
		System.out.println("LTModel构造函数");
	}
	
	/*传入参数是初始活跃节点集合*/
	@Override
	public int Activiting(Graph G, ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		G.InitIsActive();
		count=Init.size();   //初始活跃节点本身也算
		R=new ArrayList<Integer>(Init);  //将活跃节点加入R
		Set<Integer> S=new HashSet<Integer>();		//待激活节点集合,这里使用Set是为了防止存入相同节点
		//获取初始节点的粉丝节点集合
		for(Integer I:Init)
		{			
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode)) //如果邻居节点是非活跃的，加入待激活的队列
				{
					S.add(edge.desNode);
				}
			}	
		}
		while(true)  //每次循环就是一个时间步
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			//对于S中的每一个节点
			for(Integer I:S)
			{
				double threshold=G.nodeThreshold.get(I-1);
				ArrayList<Edge> edges=G.inEdges.get(I-1);
				double imfSum=0;		//边权影响力之和
				for(Edge edge:edges)
				{
					if(G.isActive.get(edge.srcNode-1))
						imfSum+=edge.edgeWeight;
				}
			
				if(imfSum>=threshold)
				{
					temp.add(I);
					G.isActive.set(I-1, true);
					count++;
				}
			}
			if(temp.isEmpty())
				break;
			
			//将 temp 中节点加入R,同时将R中所有节点粉丝节点放入S
			
			R.addAll(temp);
			S.clear();
			for(Integer I:temp)
			{
				ArrayList<Edge> edges=G.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(G.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
				}
			}	
			temp=null;
		}
//		System.out.println(G.nodeThreshold);
		return count;
	}
}
