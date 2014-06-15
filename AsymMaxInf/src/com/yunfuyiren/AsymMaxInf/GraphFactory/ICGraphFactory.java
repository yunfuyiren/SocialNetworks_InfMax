package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.GraphPro.ICGraph;

/**
 * @author wang
 * ��������ģ��ͼ�Ĺ���
 * ���Ȩֵ����ֵ������
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
