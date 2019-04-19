package com.clrs.ch22;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphBFS {
	int V;
	LinkedList<Integer> adj[]; // Adjacency Lists

	GraphBFS(int v) {
		this.V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	void BFS(int s) {
		boolean[] visited = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll();
			System.out.print(s + " ");

			LinkedList<Integer> adje = adj[s];
			Iterator<Integer> itr = adje.iterator();
			while (itr.hasNext()) {
				int n = itr.next();
				if (visited[n] != true) {
					queue.add(n);
					visited[n] = true;
				}
			}
		}
	}

	// Driver method to
	public static void main(String args[]) {
		GraphBFS g = new GraphBFS(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

		g.BFS(2);
	}
}