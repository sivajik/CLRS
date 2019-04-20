package com.clrs.ch22;

import java.util.LinkedList;

public class Exercise_22_1_5 {

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(3, 5);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);
		g.print();

		Graph gSquared = g.squareMatrixAdjList();
		gSquared.print();

	}

	static class Graph {
		int v;
		LinkedList<Integer> adj[] = null;

		public Graph(int totalVertices) {
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
		 * O(v^3) if its adj matrix
		 * 
		 */
		public Graph squareMatrixAdjList() {
			Graph squaredMatrix = new Graph(this.v);

			for (int i = 1; i < this.v; i++) {
				for (int j : adj[i]) {
					for (int k : adj[j]) {
						if (j != k & i != j) { // self loops causes nuisance
							squaredMatrix.addEdge(i, k);
						}
					}
				}
			}
			return squaredMatrix;
		}
	}

}
