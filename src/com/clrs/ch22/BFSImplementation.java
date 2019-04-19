package com.clrs.ch22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFSImplementation {

	public static void main(String[] args) {
		BFSGraph g = new BFSGraph(6);
		g.addEdge(1, 2);
		g.addEdge(1, 4);

		g.addEdge(2, 5);

		g.addEdge(3, 6);
		g.addEdge(5, 3);

		g.addEdge(4, 2);

		g.addEdge(5, 4);

		g.addEdge(6, 6);

		g.print();
		System.out.println("==========");
		g.BFS(1);

		System.out.println("========= Printing Shortest Path =========");
		g.printPath(1, 6);
	}

}

class BFSGraph {
	int v = 0;
	LinkedList<Vertex> adj[];

	Map<Integer, Vertex> map = null; // cultprit code !!!

	BFSGraph(int totalVertex) {
		this.v = totalVertex;
		this.adj = new LinkedList[this.v + 1];
		this.map = new HashMap<Integer, Vertex>();
		for (int i = 0; i < this.v + 1; i++) {
			this.adj[i] = new LinkedList<Vertex>();
			this.map.put(i, new Vertex(i));
		}
	}

	public void addEdge(int u, int v) {
		this.adj[u].add(map.get(v));
		this.adj[v].add(map.get(u));
	}

	public void print() {
		System.out.println("Printing Graph");
		for (int i = 1; i <= v; i++) {
			System.out.println("Vertex : " + (i) + " : adhList = " + this.adj[i]);
		}
	}

	public void BFS(int st) {
		System.out.println("Breadth First Search: ");
		Vertex s = map.get(st);
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(s);

		while (!q.isEmpty()) {
			Vertex u = q.remove();
			System.out.println(u.printVertex());

			LinkedList<Vertex> adjVertices = this.adj[u.vertex];
			for (Vertex v : adjVertices) {
				if (v.color == VertexColor_BFS_22.WHITE) {
					v.predecessor = u;
					v.distance = u.distance + 1;
					v.color = VertexColor_BFS_22.GRAY;
					q.add(v);
				}
			}
			u.color = VertexColor_BFS_22.BLACK;
		}
	}

	public void printPath(int s, int v) {
		if (s == v) {
			System.out.print(" -> " + s);
		} else {
			Vertex x = map.get(v);
			if (x.distance == Integer.MAX_VALUE) {
				System.out.println("There is no patch exists between these two nodes");
			} else {
				printPath(s, x.predecessor.vertex);
				System.out.print(" -> " + v);
			}
		}
	}
}

class Vertex {
	public int vertex;
	public int distance = Integer.MAX_VALUE;
	public Vertex predecessor;
	public VertexColor_BFS_22 color;

	Vertex(int vertex) {
		this.vertex = vertex;
		this.distance = 0;
		this.predecessor = null;
		this.color = VertexColor_BFS_22.WHITE;
	}

	public String toString() {
		return "" + vertex;
	}

	public String printVertex() {
		return vertex + " [parent: " + predecessor + " at distance of " + distance + "]";
	}
}

enum VertexColor_BFS_22 {
	WHITE, GRAY, BLACK;
}