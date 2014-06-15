package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.LTGraph;

/**
 * @author wang
 * 线性阈值模型图的工厂
 * 完成权值和阈值的设置
 */
public class LTGraphFactory implements GraphFactory{

	@Override
	public Graph CreateGraph(String name) throws IOException {
		// TODO Auto-generated method stub
		Graph g=new LTGraph(name);
		g.SetThreshold();
		g.SetWeight();
		return g;
	}

}
