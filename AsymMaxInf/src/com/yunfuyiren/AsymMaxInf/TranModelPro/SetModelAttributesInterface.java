package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

/**
 * @author wang
 * ����ģʽ
 * ���ݲ�ͬ������ģ�� ��LT��IC��PRP����ģ�ʹ������̽��в�ͬ�ӹ����ﵽ��ͬ�ļ���Ч����
 */
public interface SetModelAttributesInterface {
	public void SetWeight();
	public void SetThreshold();
	public double Activiting(ArrayList<Integer> Init);
}
