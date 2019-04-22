package com.clrs.ch23;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Exercise_23_2_2 {

	public static void main(String[] args) {
		Edge[] edges = new Edge[16];

		edges[0] = new Edge(0, 7, 0.16);
		edges[1] = new Edge(2, 3, 0.17);
		edges[2] = new Edge(1, 7, 0.19);
		edges[3] = new Edge(0, 2, 0.26);

		edges[4] = new Edge(5, 7, 0.28);
		edges[5] = new Edge(1, 3, 0.29);
		edges[6] = new Edge(1, 5, 0.32);
		edges[7] = new Edge(2, 7, 0.34);

		edges[8] = new Edge(4, 5, 0.35);
		edges[9] = new Edge(1, 2, 0.36);
		edges[10] = new Edge(4, 7, 0.37);
		edges[11] = new Edge(0, 4, 0.38);

		edges[12] = new Edge(6, 2, 0.40);
		edges[13] = new Edge(3, 6, 0.52);
		edges[14] = new Edge(6, 0, 0.58);
		edges[15] = new Edge(6, 4, 0.93);

		Graph g = new Graph(edges, 8);
		g.print();
		
		g.prims();
	}

	static class Edge {
		int from;
		int to;
		double weight;

		Edge(int from, int to, double weigt) {
			this.from = from;
			this.to = to;
			this.weight = weigt;
		}
	}

	static class Graph {
		double[][] adj = null;
		int v;
		Edge[] edges = null;

		Graph(Edge[] edges, int v) {
			this.v = v;
			this.edges = edges;
			this.adj = new double[v][v];
			for (int i = 0; i < v; i++) {
				adj[i] = new double[v];
			}

			for (Edge edge : edges) {
				addEdge(edge.from, edge.to, edge.weight);
			}
		}

		public void addEdge(int u, int v, double weight) {
			adj[u][v] = weight;
			adj[v][u] = weight;
		}

		public void print() {
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					System.out.print(adj[i][j] + "  ");
				}
				System.out.println();
			}
		}

		public ArrayList<Edge> getAllAdjEdges(int from) {
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for (int i = 0; i < v; i++) {
				if (adj[from][i] != 0) {
					edges.add(new Edge(from, i, adj[from][i]));
				}
			}
			return edges;
		}

		public void prims() {
			// Create a priortiy queueof edges
			PriorityQueue<Edge> p = new PriorityQueue<>(new Comparator<Edge>() {

				@Override
				public int compare(Edge first, Edge second) {
					if (first.weight < second.weight)
						return -1;
					else if (first.weight > second.weight)
						return 1;
					else
						return 0;
				}
			});

			boolean[] visited = new boolean[this.v];

			// Add any dummy edge to PQ
			p.add(edges[0]);
			visited[edges[0].from] = true;
			ArrayList<Edge> mst = new ArrayList<Edge>();

			while (!p.isEmpty()) {
				Edge currEdege = p.remove();

				if (visited[currEdege.from] && visited[currEdege.to]) {
					continue;
				}

				// for all adj of currEdge's to part,
				visited[currEdege.from] = true;
				for (Edge edgesOfTo : getAllAdjEdges(currEdege.to)) {
					if (!visited[edgesOfTo.to]) {
						p.add(edgesOfTo);
					}
				}
				visited[currEdege.to] = true;
				mst.add(currEdege);
			}
			
			System.out.println("MST: ");
			double totalWeight = 0.0d;
			for (Edge e : mst) {
				System.out.println(e.from + " - " + e.to + " : " + e.weight);
				totalWeight += e.weight;
			}
			System.out.println("total weight = " + totalWeight);
		}
	}
}
