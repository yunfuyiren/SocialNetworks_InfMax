package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;


public class TransferModel implements SetModelAttributesInterface{
	ArrayList<Integer> R;		//��ǰ�Ѽ���ڵ㼯��
	int count;			//��ǰ��Ծ�ڵ㼯�ϵĴ�С
	public TransferModel(Graph g)
	{
		System.out.println("Model���캯��");
	}
	public TransferModel()
	{
		System.out.println("Model���캯��");
	}
	@Override
	public int Activiting(Graph g, ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		return 0;
	}
}
