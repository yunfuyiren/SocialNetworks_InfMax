package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
 /**
 * @author wangyang
 * 该类实现阈值模型传播过程
 * 输入参数：网络图 G(V,E)，初始活跃节点集(节点编号List) Init 	
 * 输出参赛：被激活节点集，激活节点个数
 */
public class LTModel extends TransferModel
{		
	/*输入为一个LTGraph*/
	public LTModel(Graph g)
	{
		super(g);
		System.out.println("LTModel构造函数");
	}
	
	/*传入参数是初始活跃节点集合*/
	@Override
	public double Activiting(ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		G.InitIsActive();
		count=0;//Init.size();   //初始活跃节点本身也算
		total_inf=count;	 //总的影响力之和	
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
					total_inf+=G.nodeInfluenceWeight.get(I-1);
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
//		System.out.println("count= "+count+",total_inf= "+total_inf);
		return total_inf;
	}

	/*设置边权: 对每条边的权值定义为des节点的入度的倒数1/n*/
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		/*节点入边集合作为迭代器*/
		Iterator<ArrayList<Edge>> iter1=G.inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*对每条边的权值定义为des节点的入度的倒数1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;			
		}
	}

	/*设置节点阈值*/
	@Override
	public void SetThreshold()
	{
		Random r=new Random();
		for(int i=0;i<G.nodeNum;i++)
		{
			double v=r.nextDouble();
			G.nodeThreshold.add(v);
		}
	}
	/**
	 * @author wang
	 * 将工厂放到模型的内部作为匿名类，赋值给静态对象，既简化代码编写，又继承了工厂接口
	 */
	public static TransferModelFactory factory=new TransferModelFactory(){

		@Override
		public TransferModel CreatingModel(Graph g) {
			// TODO Auto-generated method stub
			TransferModel tm=new LTModel(g);
			return tm;
		}
		
	};
}
