package com.clrs.ch23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalImpl {

	public static void main(String[] args) {
		ArrayList<Edge> graphEdges = new ArrayList<Edge>(); // edge list, not adjacency list
		graphEdges.add(new Edge(3, 5, 2));
		graphEdges.add(new Edge(6, 7, 5));
		graphEdges.add(new Edge(3, 4, 6));
		graphEdges.add(new Edge(4, 8, 7));
		graphEdges.add(new Edge(1, 2, 9));
		graphEdges.add(new Edge(4, 5, 11));
		graphEdges.add(new Edge(1, 6, 14));
		graphEdges.add(new Edge(1, 7, 15));
		graphEdges.add(new Edge(5, 8, 16));
		graphEdges.add(new Edge(3, 6, 18));
		graphEdges.add(new Edge(3, 8, 19));
		graphEdges.add(new Edge(7, 5, 20));
		graphEdges.add(new Edge(2, 3, 24));
		graphEdges.add(new Edge(7, 8, 44));
		graphEdges.add(new Edge(6, 5, 30));

		int nodeCount = 8;
		GraphK graph = new GraphK();
		graph.kruskalMST(graphEdges, nodeCount);
	}
	
	static class GraphK {

		public void kruskalMST(ArrayList<Edge> graphEdges, int nodeCount) {
			Collections.sort(graphEdges);

			List<Edge> mstEdges = new ArrayList<Edge>();

			DisjointSet ds = new DisjointSet();
			for (int i = 1; i <= nodeCount; i++) {
				ds.makeSet(i);
			}

			for (int i = 0; i < graphEdges.size() && mstEdges.size() < nodeCount - 1; i++) {
				Edge currentEdge = graphEdges.get(i);
				Node node1 = ds.find(currentEdge.u);
				Node node2 = ds.find(currentEdge.v);

				if (node1 == node2) {

				} else {
					mstEdges.add(currentEdge);
					ds.union(currentEdge.u, currentEdge.v);
				}
			}

			long totalWegith = 0;
			for (Edge e : mstEdges) {
				totalWegith += e.weight;
				System.out.println(e);
			}
			System.out.println("=====");
			System.out.println("Weight: " + totalWegith);
			System.out.println("=====");
		}
	}

	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;

		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		public String toString() {
			return ("Edge: " + u + " -> " + v + "[" + weight + "]");
		}
	}

	static class Node {
		long data;
		Node parent;
		int rank;
	}

	static class DisjointSet {
		private Map<Long, Node> map = new HashMap<Long, Node>();

		public void makeSet(long data) {
			Node node = new Node();
			node.data = data;
			node.parent = node;
			node.rank = 0;
			map.put(data, node);
		}

		public Node find(long data) {
			return find(map.get(data));
		}

		public Node find(Node node) {
			Node parent = node.parent;
			if (parent == node) {
				return node;
			} else {
				return find(node.parent);
			}
		}

		public boolean union(long data1, long data2) {
			Node node1 = map.get(data1);
			Node node2 = map.get(data2);

			Node parent1 = find(node1);
			Node parent2 = find(node2);

			if (parent1.data == parent2.data) { // same set
				return false;
			}

			if (parent1.rank >= parent2.rank) { // does not matter non-root's rank
				parent1.rank = parent1.rank == parent2.rank ? parent1.rank + 1 : parent1.rank;
				parent2.parent = parent1;
			} else {
				parent1.parent = parent2;
			}
			return true;

		}
	}
}

