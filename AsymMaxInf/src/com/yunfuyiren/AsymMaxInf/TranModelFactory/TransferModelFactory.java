package com.yunfuyiren.AsymMaxInf.TranModelFactory;

import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;
import com.yunfuyiren.AsymMaxInf.TranModelPro.TransferModel;

public interface TransferModelFactory{
   TransferModel CreatingModel(Graph g);
}
