package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.ICGraph;

/**
 * @author wang
 * 独立级联模型图的工厂
 * 完成权值和阈值的设置
 */
public class ICGraphFactory implements GraphFactory{

	@Override
	public Graph CreateGraph(String fname) throws IOException{
		// TODO Auto-generated method stub
		Graph g=new ICGraph(fname);
		g.SetThreshold();
		g.SetWeight();
		return g;
	}

}
