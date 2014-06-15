package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wangyang
 * 该类实现独立级联模型传播过程
 */
public class ICModel extends TransferModel implements SetModelAttributesInterface{
	/*输入为一个ICGraph*/
	public ICModel()
	{
		System.out.println("ICModel构造函数");
	}
	
	/*传入参数是初始活跃节点集合*/
	@Override
	public int Activiting(Graph G,ArrayList<Integer> Init)
	{
		count=Init.size();   //初始活跃节点本身也算
		G.InitIsActive();	//活跃标记初始化，清零
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
