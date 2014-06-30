package com.yunfuyiren.AsymMaxInf.TranModelPro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.yunfuyiren.AsymMaxInf.GraphPro.Edge;
import com.yunfuyiren.AsymMaxInf.GraphPro.Graph;

/**
 * @author wang
 * 在影响力传播模型LTModel基础上，引入了保存信息传播源的方法
 * 并且加入检测器设置序列，当检测器被激活时，根据目标函数PA/DL/DT不同而设置不同
 * 激活过程
 */
public class CELF_LTModel extends TransferModel{
	private class Attri{
		int src_casc;	//级联的源节点
		double detect_time;	//从爆发直到监视器节点的距离
		Attri(){
			src_casc=0;		//爆发的源节点
			detect_time=-1;	//从发生到检测到的时间
		}
	}
	CELF_LTModel(Graph g) {
		super(g);
		// TODO Auto-generated constructor stub
		System.out.println("CELF_LTModel构造函数");
	}
	
	//输出参数
	public double DL;
	public double DT;
	public double PA;
	
	final double tThreshold=Double.MAX_VALUE;
	@Override
	public void SetWeight() {
		// TODO Auto-generated method stub
		//默认的边权为传递时间，将其设为边权
	}

	@Override
	public void SetThreshold() {
		// TODO Auto-generated method stub
		Random r=new Random();
		for(int i=0;i<G.nodeNum;i++)
		{
			double v=r.nextDouble();
			G.nodeThreshold.add(v);
		}
	}

	@Override
	public double Activiting(ArrayList<Integer> Init) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*广搜查找被级联影响的节点*/
	public void Activiting(ArrayList<Integer> A,ArrayList<Integer> Init)
	{
		DL=0;
		ArrayList<Double> dtList=new ArrayList<Double>();
		ArrayList<Double> paList=new ArrayList<Double>();
		ArrayList<Boolean> D=new ArrayList<Boolean>();		//是否节点i为检测节点的判断，这有助于加快速度
		for(int i=0;i<G.nodeNum;i++)
		{
			D.set(i, false);
		}
		for(Integer i:A)
			D.set(i-1, true);
		// TODO Auto-generated method stub				
		/*对于每一个发生的级联事件*/
		for(Integer I:Init)
		{
			ArrayList<Attri> attri=new ArrayList<Attri>(); //对于节点传播信息中保存的属性
			for(int i=0;i<G.nodeNum;i++)
			{
				Attri a=new Attri();		
				attri.add(a);
			}
			LinkedList<Integer> S=new LinkedList<Integer>();		//待激活节点集合,这里使用Set是为了防止存入相同节点
			G.InitIsActive();
			count=0;
			boolean isDetected=false;		//是否检测到
			attri.get(I-1).detect_time=0;
			attri.get(I-1).src_casc=I;
			S.push(I);
			while(!S.isEmpty())
			{
				int inum=S.poll();
				double src_time=attri.get(inum-1).detect_time;
				ArrayList<Edge> edges=G.outEdges.get(inum-1);
				for(Edge edge:edges)
				{
					double des_time=edge.edgeWeight;
					Attri a=attri.get(edge.desNode-1);
					if(a.detect_time==-1||a.detect_time>(des_time-src_time)) //如果该节点先接到当前从I节点发出的级联
					{
						a.detect_time=(des_time-src_time);
						a.src_casc=attri.get(inum-1).src_casc;
						attri.set(edge.desNode-1, a);
						S.add(edge.desNode);
					}
					if(D.get(edge.desNode-1)==true)
					{
						isDetected=true;
						break;
					}
				}	
				if(isDetected==true)
					break;
			}
			if(isDetected==true)
				DL++;
			double DTmin=Double.MAX_VALUE;
			for(int i=0;i<A.size();i++)
			{
				double t=attri.get(A.get(i)-1).detect_time;
				if(DTmin>t)
					DTmin=t;
			}
			DTmin=DTmin<tThreshold?DTmin:tThreshold;
			dtList.add(DTmin);
			double PAtmp=0;
			for(int i=0;i<G.nodeNum;i++)
			{
				if(attri.get(i).detect_time!=-1)
					PAtmp+=1;
			}
			paList.add(PAtmp);
		}	
		DL=DL/Init.size(); //标准化后的结果
		for(int i=0;i<Init.size();i++)
		{ 
			DT+=dtList.get(i);		
			PA+=paList.get(i);
		}
	}
	
	/**
	 * @author wang
	 * 将工厂放到模型的内部作为匿名类，赋值给静态对象，既简化代码编写，又继承了工厂接口
	 */
	public static TransferModelFactory factory=new TransferModelFactory(){

		@Override
		public TransferModel CreatingModel(Graph g) {
			// TODO Auto-generated method stub
			TransferModel tm=new CELF_LTModel(g);
			return tm;
		}
		
	};
}
