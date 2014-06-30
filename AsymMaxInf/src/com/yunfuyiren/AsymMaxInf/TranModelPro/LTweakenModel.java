package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

public class LTweakenModel extends TransferModel{
	double r_param;  //���������е�˥������
	public LTweakenModel(Graph g)
	{
		super(g);
		r_param=0.8;
	}
	@Override
	public double Activiting(ArrayList<Integer> Init) {
		// TODO �Զ����ɵķ������		
		G.InitIsActive();
		total_inf=0;		//�ܵ�Ӱ����֮��
		R=new ArrayList<Integer>(Init);  //����Ծ�ڵ����R
		Set<Integer> S=new HashSet<Integer>();		//������ڵ㼯��,����ʹ��Set��Ϊ�˷�ֹ������ͬ�ڵ�
		//��ȡ��ʼ�ڵ�ķ�˿�ڵ㼯��
		for(Integer I:Init)
		{
			G.isActive.set(I-1, true);
			ArrayList<Edge> edges=G.outEdges.get(I-1);
			for(Edge edge:edges)
			{
				if(!R.contains(edge.desNode)) //����ھӽڵ��Ƿǻ�Ծ�ģ����������Ķ���
				{
					S.add(edge.desNode);
				}
			}
		}
		ArrayList<Integer> R_cur=new ArrayList<Integer>(Init);	//��ǰ�ֵĻ�Ծ�ڵ㼯��
		/*�������*/
		while(true)  //ÿ��ѭ������һ��ʱ�䲽
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			//����S�е�ÿһ���ڵ�
			for(Integer I:S)
			{
				double threshold=G.nodeThreshold.get(I-1);
				ArrayList<Edge> edges=G.inEdges.get(I-1);
				double imfSum=0,imfSumcur=0; //��ȨӰ����֮�͡���һʱ��t��ȨӰ����֮��
				for(Edge edge:edges)
				{
					if(R_cur.contains(edge.srcNode))
						imfSumcur+=edge.edgeWeight;
					else if(G.isActive.get(edge.srcNode-1))
						imfSum+=edge.edgeWeight;
				}
				imfSum=imfSum*r_param+imfSumcur;  //��ʷ�ڵ�Ե�ǰ�ڵ�Ļ�Ծ��Ӱ����˥��
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
			
			//�� temp �нڵ����R,ͬʱ��R�����нڵ��˿�ڵ����S
			R.addAll(temp);
			S.clear();
			R_cur.clear();
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
	/*���ýڵ���ֵ*/
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
	
	/*���ñ�Ȩ: ��ÿ���ߵ�Ȩֵ����Ϊdes�ڵ����ȵĵ���1/n*/
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		/*�ڵ���߼�����Ϊ������*/
		Iterator<ArrayList<Edge>> iter1=G.inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*��ÿ���ߵ�Ȩֵ����Ϊdes�ڵ����ȵĵ���1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;			
		}
	}
	
	public static TransferModelFactory factory=new TransferModelFactory() {

		@Override
		public TransferModel CreatingModel(Graph g) {
			// TODO Auto-generated method stub
			TransferModel tm=new LTweakenModel(g);
			return tm;
		}
		
	};
}
