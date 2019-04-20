package com.clrs.ch22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DFSImplemetation {
	public static void main(String[] args) {
		DFSGraph g = new DFSGraph(7);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(5, 3);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);

		g.addEdge(7, 6);

		g.print();
		System.out.println("==========");
		g.DFS();

		g.printTopologocalOrder();
	}

	static class DFSGraph {
		int v;
		LinkedList<Vertex> adj[] = null;
		HashMap<Integer, Vertex> map = null;

		List<Vertex> topologicalOrder = new ArrayList<Vertex>();
		private static int counter = 0;

		DFSGraph(int totalVertices) {
			this.v = totalVertices;
			map = new HashMap<Integer, Vertex>();
			adj = new LinkedList[v + 1];
			for (int i = 1; i <= totalVertices; i++) {
				adj[i] = new LinkedList<Vertex>();
				map.put(i, new Vertex(i));
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

		public void printTopologocalOrder() {
			System.out.println("Printing Topological Ordering of the Graph");
			for (Vertex v : topologicalOrder) {
				System.out.println("Vertex : " + v);
			}
		}

		public void DFS() {
			for (int i = 1; i <= v; i++) {
				if (map.get(i).color == VertexColor.WHITE) {
					System.out.println("\n\nDisjoint Tree Started starting at vertex: " + i);
					DFSVisit(i);
				}
			}
		}

		public void DFSVisit(int u1) {
			Vertex u = map.get(u1);
			u.color = VertexColor.GRAY;
			u.startTime = counter++;
			System.out.println(u);
			for (Vertex v : adj[u1]) {
				if (v.color == VertexColor.GRAY) {
					System.out.println("\t" + u + "- > " + v + " back edge /cycle found");
				}
				if (v.color == VertexColor.BLACK) {
					System.out.println("\t" + u + "- > " + v + " cross edge found");
				}
				if (v.color == VertexColor.WHITE) {
					v.predecessor = u;
					DFSVisit(v.vertex);
				}
			}
			u.endTime = counter++;
			u.color = VertexColor.GRAY;
			topologicalOrder.add(u);
		}
	}

	static class Vertex {
		int startTime;
		int endTime;
		Vertex predecessor;
		int vertex;
		VertexColor color;

		Vertex(int vertex) {
			this.vertex = vertex;
			this.color = VertexColor.WHITE;
			this.predecessor = null;
		}

		public String toString() {
			return " " + vertex + "[" + startTime + "/" + endTime + "]";
		}
	}

	static enum VertexColor {
		WHITE, GRAY, BLACK;
	}
}
