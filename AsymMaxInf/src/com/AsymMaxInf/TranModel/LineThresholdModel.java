package com.AsymMaxInf.TranModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.AsymMaxInf.entity.EdgeEntity;
import com.AsymMaxInf.entity.NodeEntity;

 /**
 * @author wangyang
 * 该类实现阈值模型传播过程
 * 输入参数：网络图 G(V,E)，初始活跃节点集 Init 
 * 数据结构：边集：HashMap<节点编号，节点度数>，节点集：HashMap<源节点，目的节点，权值>		
 * 输出参赛：被激活节点集，激活节点个数
 */
public class LineThresholdModel 
 {
	HashMap<Integer,NodeEntity> nodes;    //节点编号及其对应的对象
	
	ArrayList<Integer> R;		//当前已激活节点集合
	int count;			//当前活跃节点集合的大小
	public LineThresholdModel()
	{
		
	}
	
	//输入为网络中的一条条边，并對模型的节点阈值，和边贡献初始化
	public LineThresholdModel(LinkedList<EdgeEntity> e,HashMap<Integer,Integer> n)
	{
		this.nodes=new HashMap<Integer,NodeEntity>();
		//节点集的建立
		Iterator<Entry<Integer,Integer>> iter=n.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer,Integer> entry=(Entry<Integer,Integer>) iter.next();
			Integer key=entry.getKey();
			Integer val=entry.getValue();
			NodeEntity node=new NodeEntity(val);
			node.threshold=Math.random();  //阈值赋值
			nodes.put(key, node);	
		}
		
		//为节点集添加入边和出边集合
		for(EdgeEntity temp:e)
		{
			int src=temp.srcNode;
			int des=temp.desNode;
			NodeEntity node=nodes.get(src);
			double l=1/(node.degree);
			node.outEdges.put(des,l);
			
			node=nodes.get(des);
			node.inEdges.put(src, l);
		}
	}	
	
	/*传入参数是初始活跃节点集合*/
	public int Activiting(ArrayList<Integer> Init)
	{
		R=new ArrayList<Integer>(Init);  //将活跃节点加入R
		ArrayList<Integer> S=new ArrayList<Integer>();		//待激活节点集合
		//获取初始节点的粉丝节点集合
		for(Integer I:Init)
		{			
			NodeEntity node=nodes.get(I);
			Iterator<Entry<Integer,Double>> iter=node.outEdges.entrySet().iterator();
			Entry<Integer,Double> entry;
			while(iter.hasNext())
			{
				entry=(Entry<Integer,Double>) iter.next();
				Integer key=entry.getKey();
				S.add(key);
			}
		}
		while(true)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(Integer I:S)
			{
				NodeEntity node=nodes.get(I);
				Iterator<Entry<Integer,Double>> iter=
						node.inEdges.entrySet().iterator();
				Entry<Integer,Double> entry;
				int acum_val=0;  //对每条激活临边的权值求和
				while(iter.hasNext())
				{
					entry=(Entry<Integer,Double>) iter.next();
					Integer key=entry.getKey();
					if(R.contains(key))   //如果该邻节点已激活
						acum_val+=entry.getValue();
				}
				if(acum_val>=node.threshold)
				{
					temp.add(I);
					count++;
				}
			}
			if(temp.isEmpty())
				break;
			
			//将 temp 中节点加入R,同时将R中所有节点粉丝节点放入S
			R.addAll(temp);
			S.clear();
			while(!temp.isEmpty())
			{
				Integer I=temp.remove(0);
				NodeEntity node=nodes.get(I);
				Iterator<Entry<Integer,Double>> iter=node.outEdges.entrySet().iterator();
				while(iter.hasNext())
				{
					Entry<Integer,Double> entry=iter.next();
					int key=entry.getKey();
					
				}
			}		
		}
		return count;
	}
}
