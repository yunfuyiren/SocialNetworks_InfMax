package com.AsymMaxInf.entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wangyang
 *	ICģ�Ͷ�Graph�̳�
 */
public class ICGraph extends Graph{
	/*���ýڵ�֮��ļ������P(i,j)*/
	public void SetICWeight()
	{
		/*�ڵ���߼�����Ϊ������*/
		Iterator<ArrayList<Edge>> iter1=inEdges.iterator();
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
}
