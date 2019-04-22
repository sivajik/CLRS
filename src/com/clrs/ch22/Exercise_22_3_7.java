package com.clrs.ch22;

import java.util.HashMap;
import java.util.LinkedList;

public class Exercise_22_3_7 {
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

		DFSGraph h = new DFSGraph(7);
		h.addEdge(1, 2);
		h.addEdge(1, 4);
		h.addEdge(2, 5);
		h.addEdge(3, 6);
		h.addEdge(5, 3);
		h.addEdge(4, 2);
		h.addEdge(5, 4);
		h.addEdge(6, 6);
		h.addEdge(7, 6);
		h.print();

		System.out.println("==========");
		g.DFS();

		System.out.println("===========");
		h.DFSNonRec();
	}

	static class DFSGraph {
		int v;
		LinkedList<Vertex> adj[] = null;
		HashMap<Integer, Vertex> map = null;

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

		public void DFS() {
			for (int i = 1; i <= v; i++) {
				if (map.get(i).color == VertexColor.WHITE) {
					DFSVisit(i);
				}
			}
		}

		public void DFSNonRec() {
			counter = 0;
			for (int i = 1; i <= v; i++) {
				if (map.get(i).color == VertexColor.WHITE) {
					DFSVisitNonREc(i);
				}
			}
		}

		public void DFSVisitNonREc(int u1) {
			Vertex u = map.get(u1);
			u.color = VertexColor.GRAY;
			u.startTime = counter++;
			System.out.println(u);
			for (Vertex v : adj[u1]) {
				if (v.color == VertexColor.WHITE) {
					v.predecessor = u;
					DFSVisitNonREc(v.vertex);
				}
			}
			u.endTime = counter++;
			u.color = VertexColor.GRAY;
		}

		public void DFSVisit(int u1) {
			Vertex u = map.get(u1);
			u.color = VertexColor.GRAY;
			u.startTime = counter++;
			System.out.println(u);
			for (Vertex v : adj[u1]) {
				if (v.color == VertexColor.WHITE) {
					v.predecessor = u;
					DFSVisit(v.vertex);
				}
			}
			u.endTime = counter++;
			u.color = VertexColor.GRAY;
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
