package com.yunfuyiren.AsymMaxInf.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.yunfuyiren.AsyMaxInf.Interface.SetGraphAttributes;

/**
 * @author wangyang
 *	IC模型对Graph继承
 */
public class ICGraph extends Graph implements SetGraphAttributes {
	public ICGraph(String fileName) throws IOException
	{
		super(fileName);    //子类调用父类构造函数
		System.out.println("PRPGraph构造函数");
	}
	/*设置节点之间的激活概率P(i,j)*/
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		/*节点入边集合作为迭代器*/
		Iterator<ArrayList<Edge>> iter1=inEdges.iterator();
		while(iter1.hasNext())
		{
			ArrayList<Edge>e=iter1.next();
			/*对每条边的激活概率定义为des节点的入度的倒数1/n*/
			Iterator<Edge> iter11=e.iterator();
			int n=e.size();
			while(iter11.hasNext())
				iter11.next().edgeWeight=(double)1/n;
			
		}
	}
	@Override
	public void SetThreshold() {
		// TODO Auto-generated method stub
		
	}
}
