package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

public class LTweakenModel extends TransferModel implements SetModelAttributesInterface{
	double r_param;  //���������е�˥������
	public LTweakenModel()
	{
		r_param=0.8;
	}
	@Override
	public int Activiting(Graph g, ArrayList<Integer> Init) {
		// TODO �Զ����ɵķ������		
		g.InitIsActive();
		count=Init.size();  //��ʼ��Ծ�ڵ㱾��Ҳ��
		R=new ArrayList<Integer>(Init);  //����Ծ�ڵ����R
		Set<Integer> S=new HashSet<Integer>();		//������ڵ㼯��,����ʹ��Set��Ϊ�˷�ֹ������ͬ�ڵ�
		//��ȡ��ʼ�ڵ�ķ�˿�ڵ㼯��
		for(Integer I:Init)
		{
			g.isActive.set(I-1, true);
			ArrayList<Edge> edges=g.outEdges.get(I-1);
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
				double threshold=g.nodeThreshold.get(I-1);
				ArrayList<Edge> edges=g.inEdges.get(I-1);
				double imfSum=0,imfSumcur=0; //��ȨӰ����֮�͡���һʱ��t��ȨӰ����֮��
				for(Edge edge:edges)
				{
					if(R_cur.contains(edge.srcNode))
						imfSumcur+=edge.edgeWeight;
					else if(g.isActive.get(edge.srcNode-1))
						imfSum+=edge.edgeWeight;
				}
				imfSum=imfSum*r_param+imfSumcur;  //��ʷ�ڵ�Ե�ǰ�ڵ�Ļ�Ծ��Ӱ����˥��
				if(imfSum>=threshold)
				{
					temp.add(I);
					g.isActive.set(I-1, true);
					count++;
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
				ArrayList<Edge> edges=g.outEdges.get(I-1);
				for(Edge e:edges)
				{
					if(g.isActive.get(e.desNode-1)==false)
						S.add(e.desNode);
				}
			}
			temp=null;
		}
		return count;
	}
	
}
