package com.AsymMaxInf.CmpedMaxInfAlgthm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.AsymMaxInf.entity.Edge;
import com.AsymMaxInf.entity.Graph;

public class MaxDegreeHeuristic {
	ArrayList<Integer> ND;	//按节点出度数由大到小排序的节点编号列表
	Graph G;
	public MaxDegreeHeuristic(String FileName) throws IOException
	{
		G=new Graph();
		G.ReadFromFile(FileName);
		ND=new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> MaxDegreeList(int k)
	{
		ArrayList<Integer> R=new ArrayList<Integer>();
		Random r=new Random();		//为后面快排设置种子
		Iterator<ArrayList<Edge>> iter1=G.inEdges.iterator();
		int[] outDegrees=new int[G.nodeNum];
		int[] index=new int[G.nodeNum];
		int i=0;
		while(iter1.hasNext())
		{
			index[i]=i+1;
			outDegrees[i]=iter1.next().size();
			i++;
		}
		
		i=r.nextInt(G.nodeNum);
		SwichPos(outDegrees,index,i,0);
		//快排对节点顺序排序
		KQuickSort(outDegrees,index,k,0,G.nodeNum-1);
		System.out.println(outDegrees);
		for(i=G.nodeNum-1;i>=G.nodeNum-k;i--)
		{
			R.add(index[i]);
		}
		return R;
	}
	//找到k个度最大的节点-用快排的思想 O(n)复杂度,输入参数表示找到第k小的,low表示当前循环的数组中的最低位，high表示最高位
	//结果保存在数组的最后部分，所以不占额外的空间
	public void KQuickSort(int[] degrees,int[] index,int k,int low,int high)
	{
		int i,j;
		i=low;  //记录比第一个元素小的数组
		j=low+1;
		while(j<=high)
		{
			if(degrees[j]<degrees[low])
			{
				i++;
				if(i!=j)
					SwichPos(degrees,index,i,j);			
			}
			j++;
		}
		SwichPos(degrees, index, i, low);
		if(i==high-k)
			return ;
		else if(i<high-k)
			KQuickSort(degrees,index,k,i+1,high);
		else
			KQuickSort(degrees,index,(k-high+i-1),low,i-1);
	}
	
	public void SwichPos(int[] degrees,int[] index,int i,int j)
	{
		int temp;
		temp=degrees[i];
		degrees[i]=degrees[j];
		degrees[j]=temp;
		
		temp=index[i];
		index[i]=index[j];
		index[j]=temp;
	}
}
