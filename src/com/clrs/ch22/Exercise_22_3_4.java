package com.clrs.ch22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Exercise_22_3_4 {

	public static void main(String[] args) {
		DFSImpl d = new DFSImpl('q', 'z');
		d.addEdge('s', 'v');
		d.addEdge('v', 'w');
		d.addEdge('w', 's');
		d.addEdge('q', 's');
		d.addEdge('q', 'w');
		d.addEdge('q', 't');
		d.addEdge('t', 'y');
		d.addEdge('y', 'q');
		d.addEdge('t', 'x');
		d.addEdge('x', 'z');
		d.addEdge('z', 'x');
		d.addEdge('r', 'y');
		d.addEdge('u', 'y');
		d.addEdge('r', 'u');

		d.printGraph();
		System.out.println("=====");
		d.DFS();
		
	}

	static enum VertexColor {
		WHITE, GRAY
	}

	static class Node {
		char vertex;
		Node predecessor;
		int sTime;
		int eTime;
		VertexColor color;

		Node(char vertex) {
			this.vertex = vertex;
			this.predecessor = null;
			this.color = VertexColor.WHITE;
			this.sTime = 0;
			this.eTime = 0;
		}
		
		public String toString() {
			return "Vertex: " + vertex + "[" + sTime + "/" + eTime + "] -> Pred= " + 
					(predecessor != null ? predecessor.vertex : '*');
		}
	}

	static class DFSImpl {
		int v;
		List<Character> adj[];

		char startChar;
		char endChar;
		
		Map<Character, Node> map = new HashMap<Character, Node>();

		DFSImpl(char sChar, char eChar) {
			this.startChar = sChar;
			this.endChar = eChar;
			this.v = ((int)eChar - (int)sChar) + 1;
			adj = new LinkedList[this.v];
			for (int i = (int) startChar; i <= (int)endChar; i++) {
				adj[i - startChar] = new LinkedList<Character>();
			}
			for (int i = (int) startChar; i <= (int)endChar; i++) {
				map.put((char) (i), new Node((char) (i)));
			}
		}

		public void addEdge(char u, char v) {
			this.adj[u - startChar].add(map.get(v).vertex);
		}

		public void DFS() {
			for (int i = (int) startChar; i <= (int)endChar; i++) {
				if (map.get((char) (i)).color == VertexColor.WHITE) {
					DFSUtil((char) (i));
				}
			}
		}

		private static int counter = 0;

		public void DFSUtil(char s) {
			Node node = map.get(s);

			node.sTime = counter++;
			node.color = VertexColor.GRAY;

			System.out.println("Visiting: " + node);
			for (char v : this.adj[s - startChar]) {
				if (map.get(v).color == VertexColor.WHITE) {
					Node vNode = map.get(v);
					vNode.predecessor = node;
					vNode.color = VertexColor.GRAY;
					DFSUtil(v);
				}
			}
			node.eTime = counter++;
		}

		public void printGraph() {
			for (int i = (int) startChar; i <= (int)endChar; i++) {
				if (adj[i-startChar].size() > 0) {
					System.out.print("Vertex: " + (char) (i) + " :: ");
					for (char c : adj[i-startChar]) {
						System.out.print(c + " ->");
					}
					System.out.println();
				}
			}
		}
	}
}