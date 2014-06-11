package com.yunfuyiren.AsymMaxInf.TranModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.entity.Edge;
import com.yunfuyiren.AsymMaxInf.entity.ICGraph;

/**
 * @author wangyang
 * ����ʵ�ֶ�������ģ�ʹ�������
 */
public class IndependCasecadeModel {
	ICGraph G;    //�ڵ��ż����Ӧ�Ķ���	
	ArrayList<Integer> R;		//��ǰ�Ѽ���ڵ㼯��
	int count;			//��ǰ��Ծ�ڵ㼯�ϵĴ�С
	
	/*����Ϊ�����е�һ�����ߣ�����ģ�͵Ľڵ���ֵ���ͱ߹��׳�ʼ��*/
	public IndependCasecadeModel(String fileName) throws IOException
	{
		G=new ICGraph();
		G.ReadFromFile(fileName);
		G.SetICWeight();

	}	
	/*��������ǳ�ʼ��Ծ�ڵ㼯��*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>();
		ArrayList<Integer> S=new ArrayList<Integer>(Init);
		Random r=new Random(35);  //���ʱȽ�
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
						if(e.edgeWeight>ran)	//�ڵ㱻����
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
