package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * ͼ�Ĺ����ӿ�
 * ���������ͼ������ѡ��ͬ�Ĺ��������ͼ�б�Ȩֵ�ͽڵ���ֵ������
 */
public interface GraphFactory {
	Graph CreateGraph(String name) throws IOException;
}
