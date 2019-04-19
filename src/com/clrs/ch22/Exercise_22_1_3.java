package com.clrs.ch22;

import java.util.LinkedList;

public class Exercise_22_1_3 {

	public static void main(String[] args) {
		Graph_22_1_3 g = new Graph_22_1_3(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(3, 5);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);

		g.print();
		System.out.println("----");
		Graph_22_1_3 gTransponsed = g.transpose();
		gTransponsed.print();
	}

}

class Graph_22_1_3 {
	int v;
	LinkedList<Integer> adj[] = null;

	public Graph_22_1_3(int totalVertices) {
		this.v = totalVertices;
		adj = new LinkedList[totalVertices + 1]; // since its 0 based
		for (int i = 0; i < totalVertices + 1; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int u, int v) {
		adj[u].add(v);
	}

	public void print() {
		for (int i = 1; i <= v; i++) {
			System.out.print("Vertex: " + (i));
			for (int edge : adj[i]) {
				System.out.print(" -> " + edge);
			}
			System.out.println("");
		}
		;
	}

	/*
	 * O(v+e) as its a full scan of graph. had it been a adj matrix, its a pure
	 * matrix transpose so O(v*v)
	 */
	public Graph_22_1_3 transpose() {
		Graph_22_1_3 g = new Graph_22_1_3(this.v);
		for (int i = 0; i < v; i++) {
			for (int edge : adj[i]) {
				g.addEdge(edge, i);
			}
		}
		return g;
	}
}
