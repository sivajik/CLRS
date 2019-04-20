package com.clrs.ch21;

import java.util.HashMap;
import java.util.Map;

public class Exercise_21_2_1 {

	public static void main(String[] args) {
		DisSet21_2_1_Impl dImpl = new DisSet21_2_1_Impl();
		for (int i = 1; i <= 10; i++) {
			dImpl.makeSet(i);
		}

		DisSet21_2_1 d1 = dImpl.union(1, 3);
		d1.print();
		d1 = dImpl.union(1, 5);
		d1.print();
		d1 = dImpl.union(7, 5);
		d1.print();
		d1 = dImpl.union(7, 9);
		d1.print();
		d1 = dImpl.union(2, 4);
		d1.print();
		d1 = dImpl.union(4, 6);
		d1.print();
	}
}

class DisSet21_2_1 {
	Node head;
	Node tail;

	public void print() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " [" + temp.parentDisSet.head.data + "] -> ");
			temp = temp.next;
		}
		System.out.println("------");
	}
}

class Node {
	int data;
	Node next;
	DisSet21_2_1 parentDisSet;
}

class DisSet21_2_1_Impl {
	Map<Integer, DisSet21_2_1> map = new HashMap<Integer, DisSet21_2_1>(); // can I avoid this??
	Map<Integer, Node> mapNodes = new HashMap<Integer, Node>(); // can I avoid this??

	public void makeSet(int data) {
		DisSet21_2_1 dSet = new DisSet21_2_1();

		Node node = new Node();
		node.data = data;
		node.next = null;

		dSet.head = node;
		dSet.tail = node;

		node.parentDisSet = dSet;
		map.put(data, dSet);
		mapNodes.put(data, node);
	}

	public int getRepresentative(DisSet21_2_1 dSet) {
		return dSet.head.data;
	}

	public DisSet21_2_1 union(int data1, int data2) {
		Node n1 = mapNodes.get(data1);
		Node n2 = mapNodes.get(data2);

		DisSet21_2_1 s1 = map.get(data1);
		DisSet21_2_1 s2 = map.get(data2);

		// link the two lists.
		s1.tail.next = n2.parentDisSet.head;

		// adjust the parents of s2.
		Node z = s2.head;
		z.parentDisSet = s1;
		while (z.next != null) {
			z.parentDisSet = s1;
			z = z.next;
		}
		z.parentDisSet = s1;
		s1.tail = s2.tail;
		map.put(data1, s1);
		map.put(data2, s1);
		s2 = null;
		return s1;
	}
}