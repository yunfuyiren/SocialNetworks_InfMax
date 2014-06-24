package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wangyang
 * ����ʵ�ֶ�������ģ�ʹ�������
 */
public class ICModel extends TransferModel implements SetModelAttributesInterface{
	/*����Ϊһ��ICGraph*/
	public ICModel(Graph g)
	{
		super(g);
		System.out.println("ICModel���캯��");
	}
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	@Override
	public double Activiting(ArrayList<Integer> Init)
	{
		count=Init.size();   //��ʼ��Ծ�ڵ㱾��Ҳ��
		total_inf=count;		//�ܵ�Ӱ����֮��
		G.InitIsActive();	//��Ծ��ǳ�ʼ��������
		R=new ArrayList<Integer>();
		ArrayList<Integer> S=new ArrayList<Integer>(Init);
		Random r=new Random();  //���ʱȽ�
		while(true)  //ÿ��ѭ������һ��ʱ�䲽
		{
			ArrayList<Integer> temp=new ArrayList<Integer>();
			for(Integer I:S)
			{
				G.isActive.set(I-1, true);
				ArrayList<Edge> edges=G.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(G.isActive.get(e.desNode-1)==false)  //������ھӽڵ�δ������
					{
						double ran=r.nextDouble();
						if(ran<e.edgeWeight)	//�ڵ㱻����
						{
							G.isActive.set(e.desNode-1, true);
							temp.add(e.desNode);
							count++;
							total_inf+=G.nodeInfluenceWeight.get(e.desNode-1);
						}
					}
				}
			}
			R.addAll(S);		//�������ͼ�����ھӵĽڵ㼯�ϼ���R
			if(temp.isEmpty())
				break;			
			S=new ArrayList<Integer>(temp);
			temp=null;
		}
//		System.out.println("count= "+count+",total_inf= "+total_inf);
		return total_inf;
	}
	
	/*���ýڵ�֮��ļ������P(i,j)*/
	@Override
	public void SetWeight() {
		// TODO �Զ����ɵķ������
		/*�ڵ���߼�����Ϊ������*/
		Iterator<ArrayList<Edge>> iter1=G.inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*��ÿ���ߵļ�����ʶ���Ϊdes�ڵ����ȵĵ���1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}

	@Override
	public void SetThreshold() {
		// TODO �Զ����ɵķ������
		
	}
}
