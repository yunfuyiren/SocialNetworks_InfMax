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
	double q;	//����ϵ��
	/*����Ϊһ��PRPGraph*/
	public PRPModel(Graph g)
	{
		super(g);
		pageRank=new ArrayList<Double>();
		q=0.85;
		System.out.println("PRPModel���캯��");
	}
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	@Override
	public double Activiting(ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		count=Init.size();   //��ʼ��Ծ�ڵ㱾��Ҳ��
		total_inf=count;		//�ܵ�Ӱ����֮��
		G.InitIsActive();
		R=new ArrayList<Integer>(Init);		   //����Ծ�ڵ����R
		Set<Integer>S=new HashSet<Integer>();  //������ڵ㼯��,����ʹ��Set��Ϊ�˷�ֹ������ͬ�ڵ�

		//��ȡ��ʼ�ڵ�ķ�˿�ڵ�ļ���
		for(Integer I:Init)
		{
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode))		//����÷�˿�ڵ���δ��������������Ķ���
				{
					S.add(edge.desNode);
				}
			}	
		}
		Random r=new Random();
		while(true) //ÿ��ѭ������һ��ʱ�䲽
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			ArrayList<Double> pActive=new ArrayList<Double>(); 
			//����������У�ÿ��ѭ��ʱ����������S�����еĴ�����ڵ�ļ�����ʣ�ȫ��������ٽ��м������
			//������������������ڵ�֮���л�����Ҫ��ȷ��һ���ڵ��״̬�������������Ĺ���
			for(Integer I:S)
			{
				double p=0;
				//����ڵ㱻����ĸ���
				for(Edge e:G.inEdges.get(I-1))
				{
					if(G.isActive.get(e.srcNode-1))
					{
						p+=e.edgeWeight;
					}
				}
				pActive.add(p);
			}
			int i=0;  //Ϊ�˱�����forѭ��ʱ��pActive��G.isActive��Ӧ
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
			
			//û�нڵ㱻���Ҳ��û����Ϣ���ݣ�Ӱ����ֹͣ����
			if(temp.isEmpty())
				break;
			
			//�� temp �нڵ����R,ͬʱ��R�����нڵ��˿�ڵ����S
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

	//ֱ�Ӹ�ÿ���ڵ��PageRank��ֵ��Ϊ1��Ȼ�����������һ������Ϊֹ��
	private void SetPageRank1()
	{
		double threshold=0.0001; //ÿ��ѭ������ʱ���ܵ�pageRankֵ�ı仯��ֵ
		double calc_pr=10000;//�����ڵ���ʱ��pageRank�仯�ۼ�ֵ
		for(int i=0;i<G.nodeNum;i++)
		{
			pageRank.add((double) 1);  //��ʼ��ֵ�趨
		}
		int time=0;   //��������
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
		SetPageRank1();		//�����ýڵ�Ȩֵ
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
		// TODO �Զ����ɵķ������
		
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
