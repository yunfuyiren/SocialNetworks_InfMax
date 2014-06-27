package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

public class PRPModel extends TransferModel{
	private ArrayList<Double> pageRank;
	double q;	//阻尼系数
	/*输入为一个PRPGraph*/
	public PRPModel(Graph g)
	{
		super(g);
		pageRank=new ArrayList<Double>();
		q=0.85;
		System.out.println("PRPModel构造函数");
	}
	
	/*传入参数是初始活跃节点集合*/
	@Override
	public double Activiting(ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		count=Init.size();   //初始活跃节点本身也算
		total_inf=count;		//总的影响力之和
		G.InitIsActive();
		R=new ArrayList<Integer>(Init);		   //将活跃节点加入R
		Set<Integer>S=new HashSet<Integer>();  //待激活节点集合,这里使用Set是为了防止存入相同节点

		//获取初始节点的粉丝节点的集合
		for(Integer I:Init)
		{
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode))		//如果该粉丝节点尚未被激活，加入待激活的队列
				{
					S.add(edge.desNode);
				}
			}	
		}
		Random r=new Random();
		while(true) //每次循环就是一个时间步
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			ArrayList<Double> pActive=new ArrayList<Double>(); 
			//激活概率序列，每次循环时，计算所有S集合中的待激活节点的激活概率，全部计算侯再进行激活操作
			//这样避免了如果两个节点之间有环，需要先确定一个节点的状态，而发生死锁的过程
			for(Integer I:S)
			{
				double p=0;
				//计算节点被激活的概率
				for(Edge e:G.inEdges.get(I-1))
				{
					if(G.isActive.get(e.srcNode-1))
					{
						p+=e.edgeWeight;
					}
				}
				pActive.add(p);
			}
			int i=0;  //为了保持在for循环时，pActive与G.isActive对应
			for(Integer I:S)
			{
				double ran=r.nextDouble();
				if(ran<pActive.get(i))
				{
					G.isActive.set(I-1,true);
					temp.add(I);
					count++;
					total_inf+=G.nodeInfluenceWeight.get(I-1);
				}
				i++;
			}
			
			//没有节点被激活，也就没有信息传递，影响力停止传递
			if(temp.isEmpty())
				break;
			
			//将 temp 中节点加入R,同时将R中所有节点粉丝节点放入S
			R.addAll(temp);
			S.clear();
			for(Integer I:temp)
			{
				for(Edge e:G.outEdges.get(I-1))
					if(G.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
			}
			temp=null;
		}		
		System.out.println("count= "+count+",total_inf= "+total_inf);
		return total_inf;
	}

	//直接给每个节点的PageRank初值设为1，然后迭代到满足一定条件为止。
	private void SetPageRank1()
	{
		double threshold=0.0001; //每次循环迭代时，总的pageRank值的变化阈值
		double calc_pr=10000;//保存在迭代时，pageRank变化累计值
		for(int i=0;i<G.nodeNum;i++)
		{
			pageRank.add((double) 1);  //初始阈值设定
		}
		int time=0;   //迭代次数
		while(calc_pr>threshold)
		{
			calc_pr=0;
			for(int i=0;i<G.nodeNum;i++)
			{
				double temp_pr=0;
				for(Edge e:G.inEdges.get(i))
				{
					temp_pr+=pageRank.get(e.srcNode-1)/G.outEdges.get(e.srcNode-1).size();
				}
				temp_pr=temp_pr*q+(1-q);//nodeNum;
				double pre_pr=pageRank.get(i);
				calc_pr+=Math.abs(temp_pr-pre_pr);
			
				pageRank.set(i, temp_pr);
			}
			time=time+1;
		}
	}

	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		SetPageRank1();		//先设置节点权值
		Iterator<ArrayList<Edge>> iter=G.inEdges.iterator();
		while(iter.hasNext())
		{
			ArrayList<Edge> edges =iter.next();
			double sum_pr=0;
			for(Edge e:edges)
			{
				sum_pr+=pageRank.get(e.srcNode-1);
			}
			for(Edge e:edges)
			{
				e.edgeWeight=pageRank.get(e.srcNode-1)/sum_pr;
			}
		}
	}

	@Override
	public void SetThreshold() {
		// TODO 自动生成的方法存根
		
	}
	
	public static TransferModelFactory factory=new TransferModelFactory() {

		@Override
		public TransferModel CreatingModel(Graph g) {
			// TODO Auto-generated method stub
			TransferModel tm=new PRPModel(g);
			return tm;
		}
		
	};
}
