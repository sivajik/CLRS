package com.clrs.ch22;

import java.util.LinkedList;

public class Exercise_22_1_4 {

	public static void main(String[] args) {
		Graph_22_1_4 g = new Graph_22_1_4(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(3, 5);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);
		g.print();

		Graph_22_1_4 gDash = g.skipMiltiEdges();
		gDash.print();

	}

	static class Graph_22_1_4 {
		int v;
		LinkedList<Integer> adj[] = null;

		public Graph_22_1_4(int totalVertices) {
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
		 * O(v+e) as its a full scan of graph. as looping through whole grpah
		 */
		public Graph_22_1_4 skipMiltiEdges() {
			Graph_22_1_4 skippedMulEdges = new Graph_22_1_4(this.v);
			int[][] tempMAatrix = new int[this.v + 1][this.v + 1];

			for (int i = 1; i < this.v; i++) {
				for (int j : adj[i]) {
					if (tempMAatrix[i][j] == 0 && i != j) {
						skippedMulEdges.addEdge(i, j);
						tempMAatrix[i][j] = 1;
					}
				}
			}
			return skippedMulEdges;
		}
	}

}
