package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * 原始的图的工厂,这个工厂是为了其它算法不用设置权值，而是直接用原始图属性来计算、选取最大影响力节点
 */
public class OriginGraphFactory implements GraphFactory{

	@Override
	public Graph CreateGraph(String name) throws IOException {
		// TODO Auto-generated method stub
		Graph g=new Graph(name);
		g.SetThreshold();
		g.SetWeight();
		return null;
	}

}
