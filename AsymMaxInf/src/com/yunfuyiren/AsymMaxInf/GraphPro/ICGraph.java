package com.yunfuyiren.AsymMaxInf.GraphPro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wangyang
 *	ICģ�Ͷ�Graph�̳�
 */
public class ICGraph extends Graph implements SetGraphAttributesInterface{
	public ICGraph(String fileName) throws IOException
	{
		super(fileName);    //������ø��๹�캯��
		System.out.println("ICGraph���캯��");
	}
	/*���ýڵ�֮��ļ������P(i,j)*/
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
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
	@Override
	public void SetThreshold() {
		// TODO Auto-generated method stub
		System.out.println("ICGraph SetThreshold");
	}
}
