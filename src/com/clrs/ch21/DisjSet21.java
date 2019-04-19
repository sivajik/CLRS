package com.clrs.ch21;

import java.util.HashMap;
import java.util.Map;

public class DisjSet21 {

	public static void main(String[] args) {
		DisJSet dSet = new DisJSet();
		for (int i = 1; i <= 10; i++) {
			dSet.makeSet(i);
		}
		
		dSet.union(2, 4);
		dSet.union(6, 4);
		dSet.union(8, 6);
		dSet.union(10, 4);
		
		dSet.union(1, 3);
		dSet.union(3, 5);
		dSet.union(5, 7);
		dSet.union(7, 9);
		
		for (int i = 1; i <= 10; i++) {
			System.out.println(dSet.find(i));
		}
		
		System.out.println(dSet.find(2));
		System.out.println(dSet.find(4));
	}

}

class VertexNode21 {
	int data;
	VertexNode21 parent;
	int rank;
	
	public String toString() {
		return "Data: " + data + " " + parent.data + " rank: " + rank;
	}
}

class DisJSet {
	Map<Integer, VertexNode21> map = new HashMap<Integer, VertexNode21>();

	public void makeSet(int data) {
		VertexNode21 v = new VertexNode21();
		v.data = data;
		v.parent = v;
		v.rank = 0;
		map.put(data, v);
	}

	public VertexNode21 find(int data) {
		return find(map.get(data));
	}

	public VertexNode21 find(VertexNode21 node) {
		if (node == node.parent) {
			return node;
		} else {
			return find(node.parent);
		}
	}

	public void union(int data1, int data2) {
		VertexNode21 parent1 = find(data1);
		VertexNode21 parent2 = find(data2);

		if (parent1.data == parent2.data) {
			// already in same set, so no thing doing.
		} else {
			if (parent1.rank >= parent2.rank) {
				parent1.rank = parent1.rank == parent2.rank ? parent1.rank + 1 : parent1.rank;
				parent2.parent = parent1;
			} else if (parent1.rank < parent2.rank) {
				parent1.parent = parent2;
			}
		}

	}
}