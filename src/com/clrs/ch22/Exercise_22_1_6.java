package com.clrs.ch22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_22_1_6 {

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(1, 2);
		g.addEdge(3, 2);
		g.addEdge(4, 2);
		g.addEdge(5, 2);
		g.addEdge(6, 2);
		g.print();

		boolean univSinkExists = g.isUnivSinkExistsSol1();
		System.out.println("Universal Sink Exists: " + univSinkExists);
	}

	static class Graph {
		int v;
		int[][] adj = null;

		Map<Integer, Integer> indegreeCounts = new HashMap<Integer, Integer>();

		public Graph(int totalVertices) {
			this.v = totalVertices;
			adj = new int[totalVertices + 1][totalVertices + 1];
			for (int i = 0; i < totalVertices + 1; i++) {
				adj[i] = new int[totalVertices + 1];
			}
		}

		public void addEdge(int u, int v) {
			adj[u][v] = 1;
			if (indegreeCounts.containsKey(v)) {
				indegreeCounts.put(v, indegreeCounts.get(v) + 1);
			} else {
				indegreeCounts.put(v, 1);
			}
		}

		public void print() {
			for (int i = 1; i <= v; i++) {
				System.out.print("Vertex: " + (i) + " ==> [ ");
				for (int j = 1; j <= v; j++) {
					System.out.print(adj[i][j] + " ");
				}
				System.out.println(" ]");
				System.out.println("");
			}
		}

		/*
		 * Universal Sink Exists - with extra space complexity of O(V) but runtime
		 * complexity is sweet.
		 */
		public boolean isUnivSinkExistsSol1() {
			List<Integer> degrees = new ArrayList(this.indegreeCounts.values());
			for (int degree : degrees) {
				if (degree == this.v - 1) {
					return true;
				}
			}
			return false;
		}

		/*
		 * Universal Sink Exists - with no extra space complexity
		 */
		public boolean isUnivSinkExistsSol2() {
			List<Integer> degrees = new ArrayList(this.indegreeCounts.values());
			for (int degree : degrees) {
				if (degree == this.v - 1) {
					return true;
				}
			}
			return false;
		}
	}

}
