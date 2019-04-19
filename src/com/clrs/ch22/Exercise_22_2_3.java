package com.clrs.ch22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Exercise_22_2_3 {

	public static void main(String[] args) {
		BFSSingleBitGraph g = new BFSSingleBitGraph(6);
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

class BFSSingleBitGraph {
	private int v;
	List<VertexSingleBit>[] adj = null;

	Map<Integer, VertexSingleBit> map = new HashMap<Integer, VertexSingleBit>();

	BFSSingleBitGraph(int totalVertices) {
		this.v = totalVertices;
		adj = new LinkedList[v + 1];

		for (int i = 1; i <= v; i++) {
			adj[i] = new LinkedList<VertexSingleBit>();
			map.put(i, new VertexSingleBit(i));
		}
	}

	public void addEdge(int u, int v) {
		adj[u].add(map.get(v));
	}

	public void print() {
		System.out.println("Printing Graph");
		for (int i = 1; i <= v; i++) {
			System.out.println("Vertex : " + (i) + " : adhList = " + this.adj[i]);
		}
	}

	public void BFS(int s) {
		VertexSingleBit u = map.get(s);
		u.visited = true;
		Queue<VertexSingleBit> q = new LinkedList<VertexSingleBit>();
		q.add(u);

		while (!q.isEmpty()) {
			VertexSingleBit v = q.remove();
			System.out.println("Visiting: " + v + " Q=" + q);
			for (VertexSingleBit adjVertex : adj[v.vertex]) {
				if (!adjVertex.visited) {
					q.add(adjVertex);
					adjVertex.visited = true;
				}
			}
			v.visited = true;
		}
	}
}

class VertexSingleBit {
	int vertex;
	boolean visited;

	public VertexSingleBit(int vertex) {
		this.visited = false;
		this.vertex = vertex;
	}

	public String toString() {
		return "Vertex: " + vertex + " " + visited;
	}
}
