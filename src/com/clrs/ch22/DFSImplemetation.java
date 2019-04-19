package com.clrs.ch22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.sun.org.apache.xml.internal.security.exceptions.AlgorithmAlreadyRegisteredException;
import javafx.scene.input.ScrollEvent.VerticalTextScrollUnits;

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
}

class DFSGraph {
	int v;
	LinkedList<Vertex_22> adj[] = null;
	HashMap<Integer, Vertex_22> map = null;

	List<Vertex_22> topologicalOrder = new ArrayList<Vertex_22>();
	private static int counter = 0;

	DFSGraph(int totalVertices) {
		this.v = totalVertices;
		map = new HashMap<Integer, Vertex_22>();
		adj = new LinkedList[v + 1];
		for (int i = 1; i <= totalVertices; i++) {
			adj[i] = new LinkedList<Vertex_22>();
			map.put(i, new Vertex_22(i));
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
		for (Vertex_22 v : topologicalOrder) {
			System.out.println("Vertex : " + v);
		}
	}

	public void DFS() {
		for (int i = 1; i <= v; i++) {
			if (map.get(i).color == VertexColor_DFS_22.WHITE) {
				System.out.println("\n\nDisjoint Tree Started starting at vertex: " + i);
				DFSVisit(i);
			}
		}
	}

	public void DFSVisit(int u1) {
		Vertex_22 u = map.get(u1);
		u.color = VertexColor_DFS_22.GRAY;
		u.startTime = counter++;
		System.out.println(u);
		for (Vertex_22 v : adj[u1]) {
			if (v.color == VertexColor_DFS_22.GRAY) {
				System.out.println("\t" + u + "- > " + v + " back edge /cycle found");
			}
			if (v.color == VertexColor_DFS_22.BLACK) {
				System.out.println("\t" + u + "- > " + v + " cross edge found");
			}
			if (v.color == VertexColor_DFS_22.WHITE) {
				v.predecessor = u;
				DFSVisit(v.vertex);
			}
		}
		u.endTime = counter++;
		u.color = VertexColor_DFS_22.GRAY;
		topologicalOrder.add(u);
	}
}

class Vertex_22 {
	int startTime;
	int endTime;
	Vertex_22 predecessor;
	int vertex;
	VertexColor_DFS_22 color;

	Vertex_22(int vertex) {
		this.vertex = vertex;
		this.color = VertexColor_DFS_22.WHITE;
		this.predecessor = null;
	}

	public String toString() {
		return " " + vertex + "[" + startTime + "/" + endTime + "]";
	}
}

enum VertexColor_DFS_22 {
	WHITE, GRAY, BLACK;
}