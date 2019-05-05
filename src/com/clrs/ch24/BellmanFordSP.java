package com.clrs.ch24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFordSP {

	public static void main(String[] args) {
		Graph graph = new Graph(5, 8);
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 3);

		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 4, 2);
		graph.addEdge(3, 2, 5);

		graph.addEdge(3, 1, 1);
		graph.addEdge(4, 3, -3);

		graph.printAGraph();

		graph.bellmanFord(0);
		graph.printAGraph();

		System.out.println();
		graph.printPath(0, 1);
		System.out.println();
		graph.printPath(0, 2);
		System.out.println();
		graph.printPath(0, 3);
		System.out.println();
		graph.printPath(0, 4);
	}

	static class Edge {
		int src;
		int dest;
		int weight;

		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		Edge() {
		};
	}

	static class Vertex {
		int id;
		Vertex predecessor;
		int distFromSourceVertex;

		Vertex(int id) {
			this.id = id;
			this.predecessor = null;
			this.distFromSourceVertex = Integer.MAX_VALUE;
		}

		public String toString() {
			return "id: " + id + " distance: " + distFromSourceVertex + " pred: "
					+ (predecessor != null ? "" + predecessor.id : "*");
		}
	}

	static class Graph {
		List<Edge> edges;

		List<Vertex>[] adjList = null;
		Map<Integer, Vertex> map = new HashMap<Integer, Vertex>();

		int v;
		int e;

		Graph(int totalVertices, int totalEdges) {
			this.v = totalVertices;
			this.e = totalEdges;
			edges = new ArrayList<Edge>();
			adjList = new ArrayList[v];
			for (int i = 0; i < v; i++) {
				adjList[i] = new ArrayList<Vertex>();
				map.put(i, new Vertex(i));
			}
		}

		public void addEdge(int u, int v, int weight) {
			edges.add(new Edge(u, v, weight));
			adjList[u].add(map.get(v));
		}

		void printAGraph() {
			System.out.println("Vertex distance from Source");
			for (int i = 0; i < v; ++i) {
				System.out.println(i + "\t" + map.get(i));
			}
			System.out.println();
		}

		void printPath(int s, int v) {
			if (s == v) {
				return;
			} else {
				printPath(s, map.get(v).predecessor.id);
				System.out.print(" -> " + v);
			}
		}

		public void bellmanFord(int s) {
			for (int i = 0; i < v; i++) {
				Vertex vert = map.get(i);
				vert.predecessor = null; // waste code
				vert.distFromSourceVertex = Integer.MAX_VALUE; // waste code
			}

			Vertex start = map.get(s);
			start.distFromSourceVertex = 0;

			for (int i = 0; i < v - 1; i++) {
				for (Edge edge : edges) {
					int u = edge.src;
					int v = edge.dest;
					int weight = edge.weight;

					if (map.get(u).distFromSourceVertex + weight < map.get(v).distFromSourceVertex) {
						map.get(v).distFromSourceVertex = map.get(u).distFromSourceVertex + weight;
						map.get(v).predecessor = map.get(u);
					}
				}
			}

			for (int i = 0; i < v - 1; i++) {
				for (Edge edge : edges) {
					int u = edge.src;
					int v = edge.dest;
					int weight = edge.weight;

					if (map.get(u).distFromSourceVertex + weight < map.get(v).distFromSourceVertex) {
						throw new RuntimeException("Negative cycles Exists in the graph");
					}
				}
			}
		}

	}
}
