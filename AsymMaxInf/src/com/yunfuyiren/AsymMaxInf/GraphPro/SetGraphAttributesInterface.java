package com.yunfuyiren.AsymMaxInf.GraphPro;

/**
 * @author wang
 * 图的属性接口
 * 设置不同网络的一些属性，对Graph类的加工
 * 如：边权、阈值等
 */
public interface SetGraphAttributesInterface {
	public void SetWeight();
	public void SetThreshold();
	public void SetInfluceWeight();		//设置节点影响力权值
	public void SetDefaultInfluceWeight();	//设置节点默认的影响力权值为1
}
