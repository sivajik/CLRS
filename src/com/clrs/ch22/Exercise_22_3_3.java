package com.clrs.ch22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Assumption: as given in 22.3.2 I took a assumption that there are no
 * disconnected vertices here. All nodes are from startChar to endChar
 *
 */
public class Exercise_22_3_3 {

	public static void main(String[] args) {
		DFSImpl d = new DFSImpl('u', 'z');
		d.addEdge('u', 'v');
		d.addEdge('u', 'x');
		d.addEdge('x', 'v');
		d.addEdge('v', 'y');
		d.addEdge('y', 'x');
		d.addEdge('w', 'y');
		d.addEdge('w', 'z');
		d.addEdge('z', 'z');

		d.printGraph();
		System.out.println("=====");
		d.DFS();

	}

	static enum VertexColor {
		WHITE, GRAY, BLACK
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
			return "Vertex: " + vertex + "[" + sTime + "/" + eTime + "] -> Pred= "
					+ (predecessor != null ? predecessor.vertex : '*');
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
			this.v = 1 + (int) (eChar - sChar);
			adj = new LinkedList[this.v];
			for (int i = (int) startChar; i <= (int) endChar; i++) {
				adj[i - startChar] = new LinkedList<Character>();
			}
			for (int i = (int) startChar; i <= (int) endChar; i++) {
				map.put((char) (i), new Node((char) (i)));
			}
		}

		public void addEdge(char u, char v) {
			this.adj[u - startChar].add(map.get(v).vertex);
		}

		public void DFS() {
			for (int i = (int) startChar; i <= (int) endChar; i++) {
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

			System.out.print("( " + node.vertex + " ");
			for (char v : this.adj[s - startChar]) {
				if (map.get(v).color == VertexColor.WHITE) {
					Node vNode = map.get(v);
					vNode.predecessor = node;
					vNode.color = VertexColor.GRAY;
					DFSUtil(v);
				}
			}
			System.out.print(node.vertex + " ) ");
			node.eTime = counter++;
			node.color = VertexColor.BLACK;
		}

		public void printGraph() {
			for (int i = (int) startChar; i <= (int) endChar; i++) {
				if (adj[i - startChar].size() > 0) {
					System.out.print("Vertex: " + (char) (i) + " :: ");
					for (char c : adj[i - startChar]) {
						System.out.print(c + " ->");
					}
					System.out.println();
				}
			}
		}
	}
}