package com.yunfuyiren.AsymMaxInf.TranModel;

import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsyMaxInf.Interface.SetModelAttributes;
import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.ICGraph;

/**
 * @author wangyang
 * ����ʵ�ֶ�������ģ�ʹ�������
 */
public class IndependCasecadeModel extends TransferModel implements SetModelAttributes{
	ICGraph G;    //�ڵ��ż����Ӧ�Ķ���	

	/*����Ϊһ��ICGraph*/
	public IndependCasecadeModel(ICGraph graph)
	{
		G=graph;
		G.SetWeight();
	}
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	public int Activiting(ArrayList<Integer> Init)
	{
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
		return count;
	}
}
