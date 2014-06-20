package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.PRPGraph;

/**
 * @author wang
 * PageRank模型的图的工厂
 */
public class PRPGraphFactory implements GraphFactory{

	@Override
	public Graph CreateGraph(String name) throws IOException {
		// TODO Auto-generated method stub
		Graph g=new PRPGraph(name);
		g.SetThreshold();
		g.SetWeight();
//		g.SetInfluceWeight();
		g.SetDefaultInfluceWeight();
		return g;
	}

}
