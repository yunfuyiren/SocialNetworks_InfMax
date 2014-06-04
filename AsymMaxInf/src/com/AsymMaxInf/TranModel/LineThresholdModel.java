package com.AsymMaxInf.TranModel;

import java.util.HashMap;
import java.util.LinkedList;

import com.AsymMaxInf.entity.EdgeEntity;

 /**
 * @author wangyang
 * 该类实现阈值模型传播过程
 * 输入参数：网络的边图 a-->b，初始活跃节点集
 * 输出参赛：被激活节点集，激活节点个数
 */
public class LineThresholdModel 
 {
	HashMap<Integer,Float> nodes;    //节点及其对应的阈值
	HashMap<EdgeEntity,Float> edges;	//边及其对应的影响力
	public LineThresholdModel()
	{
		
	}
	
	//输入为网络中的一条条边，并對模型的节点阈值，和边贡献初始化
	public LineThresholdModel(LinkedList<EdgeEntity> edges)
	{
		int n=edges.size();
		while(n>0)
		{
			
		}
	}
	
	
}
