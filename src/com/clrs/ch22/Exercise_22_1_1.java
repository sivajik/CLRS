package com.clrs.ch22;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Exercise_22_1_1 {

	public static void main(String[] args) {
		Graph_22_1_1 g = new Graph_22_1_1(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(3, 5);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);

		g.calcOutDegree();
		System.out.println("\n");
		g.calcInDegree();
	}

}

class Graph_22_1_1 {
	int v;
	LinkedList<Integer> adj[] = null;

	public Graph_22_1_1(int totalVertices) {
		this.v = totalVertices;
		adj = new LinkedList[totalVertices + 1]; // since its 0 based
		for (int i = 0; i < totalVertices + 1; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int u, int v) {
		adj[u].add(v);
	}

	/*
	 * O(v) as its a full scan of vertices (assuming size method is unit cost)
	 * O(v+e) as its a full scan of entire graph and there is no way we can get size
	 * of the adj[u]
	 */
	public void calcOutDegree() {
		System.out.println("Outdegree Calcualtion");
		for (int i = 0; i < v; i++) {
			System.out.println("Vertex : " + (i) + " : outdegree= " + adj[i].size());
		}
	}

	/*
	 * O(v+e) as its a full scan of graph (end to end)
	 */
	public void calcInDegree() {
		System.out.println("Indegree Calcualtion");
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < v; i++) {
			for (int eachEdge : adj[i]) {
				if (map.containsKey(eachEdge)) {
					map.put(eachEdge, 1 + map.get(eachEdge));
				} else {
					map.put(eachEdge, 1);
				}
			}
		}
		map.forEach((k, v) -> {
			System.out.println("Vertex : " + (k) + " : indegree= " + v);
		});
	}
}
