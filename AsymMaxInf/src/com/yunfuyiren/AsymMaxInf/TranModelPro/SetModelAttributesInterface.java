package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * 工厂模式
 * 根据不同的输入模型 如LT、IC、PRP，对模型传播过程进行不同加工，达到不同的激活效果。
 */
public interface SetModelAttributesInterface {
	public int Activiting(Graph g,ArrayList<Integer> Init);
}
