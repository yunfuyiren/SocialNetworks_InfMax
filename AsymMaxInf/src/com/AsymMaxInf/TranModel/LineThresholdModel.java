package com.AsymMaxInf.TranModel;

import java.util.HashMap;
import java.util.LinkedList;

import com.AsymMaxInf.entity.EdgeEntity;

 /**
 * @author wangyang
 * ����ʵ����ֵģ�ʹ�������
 * �������������ı�ͼ a-->b����ʼ��Ծ�ڵ㼯
 * ���������������ڵ㼯������ڵ����
 */
public class LineThresholdModel 
 {
	HashMap<Integer,Float> nodes;    //�ڵ㼰���Ӧ����ֵ
	HashMap<EdgeEntity,Float> edges;	//�߼����Ӧ��Ӱ����
	public LineThresholdModel()
	{
		
	}
	
	//����Ϊ�����е�һ�����ߣ�����ģ�͵Ľڵ���ֵ���ͱ߹��׳�ʼ��
	public LineThresholdModel(LinkedList<EdgeEntity> edges)
	{
		int n=edges.size();
		while(n>0)
		{
			
		}
	}
	
	
}
