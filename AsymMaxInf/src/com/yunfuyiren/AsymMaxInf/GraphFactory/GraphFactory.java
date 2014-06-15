package com.yunfuyiren.AsymMaxInf.GraphFactory;

import java.io.IOException;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * 图的工厂接口
 * 根据输入的图的类型选择不同的工厂，完成图中边权值和节点阈值的设置
 */
public interface GraphFactory {
	Graph CreateGraph(String name) throws IOException;
}
