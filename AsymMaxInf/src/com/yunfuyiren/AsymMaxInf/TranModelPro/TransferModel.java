package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;


public abstract class TransferModel implements SetModelAttributesInterface{
	ArrayList<Integer> R;		//��ǰ�Ѽ���ڵ㼯��
	int count;			//��ǰ��Ծ�ڵ㼯�ϵĴ�С
	Graph G;			//����ģ�͵�����ͼ
	double total_inf; 	//�����ܵ�Ӱ���� 
	public TransferModel(Graph g)
	{
		G=g;
		System.out.println("Model���캯��");
	}
}
