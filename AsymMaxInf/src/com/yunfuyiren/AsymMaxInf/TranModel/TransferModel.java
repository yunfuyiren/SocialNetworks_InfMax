package com.yunfuyiren.AsymMaxInf.TranModel;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.entity.Graph;

abstract class TransferModel {
	ArrayList<Integer> R;		//��ǰ�Ѽ���ڵ㼯��
	int count;			//��ǰ��Ծ�ڵ㼯�ϵĴ�С
	public TransferModel(Graph graph)
	{
		
	}
	public TransferModel()
	{
		
	}
	public abstract int Activiting(ArrayList<Integer> Init);
}
