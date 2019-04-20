package com.clrs.ch21;

import java.util.HashMap;
import java.util.Map;

public class Exercise_21_3_2 {

	public static void main(String[] args) {
		DisSet21_3_2_Impl d = new DisSet21_3_2_Impl();
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
	}
}

class Node_21_3_2 {
	int data;
	Node_21_3_2 parent;
	int rank;

	public String toString() {
		return "Data : " + data + " Parent: " + parent.data + " Rank: " + rank;
	}
}

class DisSet21_3_2_Impl {
	Map<Integer, Node_21_3_2> map = new HashMap<Integer, Node_21_3_2>(); // can I avoid this??

	public void makeSet(int data) {
		Node_21_3_2 node = new Node_21_3_2();
		node.data = data;
		node.parent = node;
		node.rank = 0;
		map.put(data, node);
	}

	public Node_21_3_2 find(int data) {
		return findNonRecursive(map.get(data));
	}

	public Node_21_3_2 findNonRecursive(Node_21_3_2 node) {
		Node_21_3_2 temp = node;
		while (temp != temp.parent) {
			temp = temp.parent;
		}
		return temp;
	}

	public void union(int data1, int data2) {
		Node_21_3_2 n1 = map.get(data1);
		Node_21_3_2 n2 = map.get(data2);

		Node_21_3_2 s1 = findNonRecursive(n1);
		Node_21_3_2 s2 = findNonRecursive(n2);

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
}