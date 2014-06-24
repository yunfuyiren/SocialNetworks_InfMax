package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wangyang
 * 该类实现独立级联模型传播过程
 */
public class ICModel extends TransferModel implements SetModelAttributesInterface{
	/*输入为一个ICGraph*/
	public ICModel(Graph g)
	{
		super(g);
		System.out.println("ICModel构造函数");
	}
	
	/*传入参数是初始活跃节点集合*/
	@Override
	public double Activiting(ArrayList<Integer> Init)
	{
		count=Init.size();   //初始活跃节点本身也算
		total_inf=count;		//总的影响力之和
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
							total_inf+=G.nodeInfluenceWeight.get(e.desNode-1);
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
//		System.out.println("count= "+count+",total_inf= "+total_inf);
		return total_inf;
	}
	
	/*设置节点之间的激活概率P(i,j)*/
	@Override
	public void SetWeight() {
		// TODO 自动生成的方法存根
		/*节点入边集合作为迭代器*/
		Iterator<ArrayList<Edge>> iter1=G.inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*对每条边的激活概率定义为des节点的入度的倒数1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}

	@Override
	public void SetThreshold() {
		// TODO 自动生成的方法存根
		
	}
}
