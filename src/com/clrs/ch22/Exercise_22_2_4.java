package com.clrs.ch22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Exercise_22_2_4 {

	public static void main(String[] args) {
		BFSSingleBitGraphAdjMatrix g = new BFSSingleBitGraphAdjMatrix(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(5, 3);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);

		g.print();
		System.out.println("==========");

		g.BFS(1);
	}
}

class BFSSingleBitGraphAdjMatrix {
	private int v;
	int[][] adj = null;

	Map<Integer, VertexSingleBit> map = new HashMap<Integer, VertexSingleBit>();

	BFSSingleBitGraphAdjMatrix(int totalVertices) {
		this.v = totalVertices;
		adj = new int[v + 1][v + 1];

		for (int i = 1; i <= v; i++) {
			map.put(i, new VertexSingleBit(i));
			for (int j = 1; j <= v; j++) {
				adj[i][j] = 0;
			}
		}
	}

	public void addEdge(int u, int v) {
		adj[u][v] = 1;
	}

	public void print() {
		System.out.println("Printing Graph");
		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}

	public List<VertexSingleBit> adjVertices(int u) {
		List<VertexSingleBit> result = new ArrayList<VertexSingleBit>();
		for (int i = 1; i <= v; i++) {
			if (adj[u][i] == 1) {
				result.add(map.get(i));
			}
		}
		return result;
	}

	public void BFS(int s) {
		VertexSingleBit u = map.get(s);
		u.visited = true;
		Queue<VertexSingleBit> q = new LinkedList<VertexSingleBit>();
		q.add(u);

		while (!q.isEmpty()) {
			VertexSingleBit v = q.remove();
			System.out.println("Visiting: " + v + " Queue =" + q);
			for (VertexSingleBit adjVertex : adjVertices(v.vertex)) {
				if (!adjVertex.visited) {
					q.add(adjVertex);
					adjVertex.visited = true;
				}
			}
			v.visited = true;
		}
	}
}
