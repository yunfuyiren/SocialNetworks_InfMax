package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.LTGraph;

/**
 * @author wang
 * ������ֵģ��ͼ�Ĺ���
 * ���Ȩֵ����ֵ������
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
