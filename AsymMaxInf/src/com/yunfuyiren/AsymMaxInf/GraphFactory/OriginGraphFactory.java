package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * ԭʼ��ͼ�Ĺ���,���������Ϊ�������㷨��������Ȩֵ������ֱ����ԭʼͼ���������㡢ѡȡ���Ӱ�����ڵ�
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
