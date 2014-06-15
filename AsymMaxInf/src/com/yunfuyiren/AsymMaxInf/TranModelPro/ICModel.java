package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wangyang
 * ����ʵ�ֶ�������ģ�ʹ�������
 */
public class ICModel extends TransferModel implements SetModelAttributesInterface{
	/*����Ϊһ��ICGraph*/
	public ICModel()
	{
		System.out.println("ICModel���캯��");
	}
	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	@Override
	public int Activiting(Graph G,ArrayList<Integer> Init)
	{
		count=Init.size();   //��ʼ��Ծ�ڵ㱾��Ҳ��
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
