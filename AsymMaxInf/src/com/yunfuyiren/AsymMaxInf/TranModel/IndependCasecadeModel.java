package com.yunfuyiren.AsymMaxInf.TranModel;

import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsyMaxInf.Interface.SetModelAttributes;
import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.ICGraph;

/**
 * @author wangyang
 * 该类实现独立级联模型传播过程
 */
public class IndependCasecadeModel extends TransferModel implements SetModelAttributes{
	ICGraph G;    //节点编号及其对应的对象	

	/*输入为一个ICGraph*/
	public IndependCasecadeModel(ICGraph graph)
	{
		G=graph;
		G.SetWeight();
	}
	
	/*传入参数是初始活跃节点集合*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>();
		ArrayList<Integer> S=new ArrayList<Integer>(Init);
		Random r=new Random();  //概率比较
		while(true)  //每次循环就是一个时间步
		{
			ArrayList<Integer> temp=new ArrayList<Integer>();
			for(Integer I:S)
			{
				G.isActive.set(I-1, true);
				ArrayList<Edge> edges=G.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(G.isActive.get(e.desNode-1)==false)  //如果该邻居节点未被激活
					{
						double ran=r.nextDouble();
						if(ran<e.edgeWeight)	//节点被激活
						{
							G.isActive.set(e.desNode-1, true);
							temp.add(e.desNode);
							count++;
						}
					}
				}
			}
			R.addAll(S);		//把这次试图激活邻居的节点集合加入R
			if(temp.isEmpty())
				break;			
			S=new ArrayList<Integer>(temp);
			temp=null;
		}
		return count;
	}
}
