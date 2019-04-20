package com.clrs.ch21;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Exercise_21_3_1 {

	public static void main(String[] args) {
		DisSet21_3_1_Impl d = new DisSet21_3_1_Impl();
		for (int i = 1; i <= 16; i++) {
			d.makeSet(i);
		}

		for (int i = 1; i <= 15; i = i + 2) {
			d.union(i, i + 1);
		}

		for (int i = 1; i <= 13; i = i + 4) {
			d.union(i, i + 2);
		}

		d.union(1, 5);
		d.union(11, 13);
		d.union(1, 10);
		System.out.println(d.find(2));
		System.out.println(d.find(9));

		/*
		 * Problem 21_3_4
		 */
		System.out.println("\n\n\nProblem: 21_3_4");
		DisSet21_3_1_Impl d1 = new DisSet21_3_1_Impl();
		for (int i = 1; i <= 9; i++) {
			d1.makeSet(i);
		}
		d1.union(1, 6);
		d1.union(2, 7);
		d1.union(3, 8);
		d1.union(4, 9);
		d1.union(2, 6);

		d1.printSet(1);
		d1.printSet(3);
		d1.printSet(9);
	}
}

class Node_21_3_1 {
	int data;
	Node_21_3_1 parent;
	int rank;
	int representative;

	public String toString() {
		return "Data : " + data + " Parent: " + parent.data + " Rank: " + rank + " Rep: " + representative;
	}
}

class DisSet21_3_1_Impl {
	Map<Integer, Node_21_3_1> map = new HashMap<Integer, Node_21_3_1>(); // can I avoid this??

	public void makeSet(int data) {
		Node_21_3_1 node = new Node_21_3_1();
		node.data = data;
		node.parent = node;
		node.rank = 0;
		map.put(data, node);
	}

	public Node_21_3_1 find(int data) {
		return find(map.get(data));
	}

	public Node_21_3_1 find(Node_21_3_1 node) {
		if (node == node.parent) {
			return node;
		} else {
			return find(node.parent);
		}
	}

	public void union(int data1, int data2) {
		Node_21_3_1 n1 = map.get(data1);
		Node_21_3_1 n2 = map.get(data2);

		Node_21_3_1 s1 = find(n1);
		Node_21_3_1 s2 = find(n2);

		if (s1.data == s2.data) {
			// do nothing as the are from same set.
		}

		if (s1.rank >= s2.rank) {
			s1.rank = (s1.rank == s2.rank) ? 1 + s1.rank : s1.rank;
			s2.parent = s1;
		} else {
			s1.parent = s2;
		}
	}

	public int representative(Node_21_3_1 node) {
		if (node == node.parent) {
			return node.data;
		} else {
			return representative(node.parent);
		}
	}

	public void prepareMapOfReps() {
		Set<Integer> set = map.keySet();
		Iterator<Integer> itr = set.iterator();
		while (itr.hasNext()) {
			Node_21_3_1 node = map.get(itr.next());
			int representative = representative(node);
			node.representative = representative;
		}
	}

	public void printSet(int data) {
		prepareMapOfReps();

		System.out.println("Printing Entire set of : " + data);
		Node_21_3_1 n1 = map.get(data);
		Node_21_3_1 s1 = find(n1);
		int representative = s1.representative;

		map.forEach((key, value) -> {
			if (value.representative == representative) {
				System.out.print(value.data + " -> ");
			}
		});
		System.out.println();
	}
}