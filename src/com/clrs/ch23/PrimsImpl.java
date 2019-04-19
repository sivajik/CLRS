package com.clrs.ch23;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsImpl {

	public static void main(String[] args) {
		EDGE[] edges = new EDGE[16];

		edges[0] = new EDGE(0, 7, 0.16);
		edges[1] = new EDGE(2, 3, 0.17);
		edges[2] = new EDGE(1, 7, 0.19);
		edges[3] = new EDGE(0, 2, 0.26);

		edges[4] = new EDGE(5, 7, 0.28);
		edges[5] = new EDGE(1, 3, 0.29);
		edges[6] = new EDGE(1, 5, 0.32);
		edges[7] = new EDGE(2, 7, 0.34);

		edges[8] = new EDGE(4, 5, 0.35);
		edges[9] = new EDGE(1, 2, 0.36);
		edges[10] = new EDGE(4, 7, 0.37);
		edges[11] = new EDGE(0, 4, 0.38);

		edges[12] = new EDGE(6, 2, 0.40);
		edges[13] = new EDGE(3, 6, 0.52);
		edges[14] = new EDGE(6, 0, 0.58);
		edges[15] = new EDGE(6, 4, 0.93);

		ArrayList<ArrayList<EDGE>> graph = createGraph(edges);
		ArrayList<EDGE> mst = prims(graph);

		System.out.println("MST: ");
		for (EDGE e : mst) {
			System.out.println(e.from + " - " + e.to + " : " + e.weight);
		}
	}

	public static ArrayList<EDGE> prims(ArrayList<ArrayList<EDGE>> G) {
		if (G.isEmpty())
			throw new NullPointerException("The Graph is empty");

		ArrayList<EDGE> mst = new ArrayList<>();
		PriorityQueue<EDGE> pq = new PriorityQueue<>((Object o1, Object o2) -> {
			EDGE first = (EDGE) o1;
			EDGE second = (EDGE) o2;

			if (first.weight < second.weight)
				return -1;
			else if (first.weight > second.weight)
				return 1;
			else
				return 0;
		});

		for (EDGE e : G.get(0)) {
			pq.add(e);
		}

		boolean[] marked = new boolean[G.size()];
		marked[0] = true;
		while (!pq.isEmpty()) {
			EDGE e = pq.peek();

			pq.poll();
			if (marked[e.from] && marked[e.to])
				continue;
			marked[e.from] = true;
			for (EDGE edge : G.get(e.to)) {
				if (!marked[edge.to]) {
					pq.add(edge);
				}
			}
			marked[e.to] = true;
			mst.add(e);

		}
		return mst;
	}

	public static ArrayList<ArrayList<EDGE>> createGraph(EDGE[] edges) {
		ArrayList<ArrayList<EDGE>> G = new ArrayList<>();
		int length = edges.length * 2;
		for (int i = 0; i < length; ++i) {
			G.add(new ArrayList<>());
		}

		for (EDGE e : edges) {
			EDGE other = new EDGE(e.to, e.from, e.weight);
			G.get(e.from).add(e);
			G.get(e.to).add(other);
			System.out.println("Added edge [" + e.from + ", " + e.to + " : " + e.weight + "] " + "[" + e.to + ", "
					+ e.from + " : " + e.weight + "]");
		}

		return G;
	}

	static class EDGE {
		int from;
		int to;
		double weight;

		EDGE(int from, int to, double weigt) {
			this.from = from;
			this.to = to;
			this.weight = weigt;
		}
	}
}