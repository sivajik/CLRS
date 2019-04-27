package com.clrs.ch12;

public class Exercise_12_2_2 {

	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.addNode(2);
		bst.addNode(5);
		bst.addNode(1);
		bst.addNode(7);
		bst.addNode(6);
		bst.addNode(8);

		bst.inOrder();
		System.out.println("Min: Rec: " + bst.treeMinRec());
		System.out.println("Min: Itr: " + bst.treeMinItr());

		System.out.println("Max: Rec: " + bst.treeMaxRec());
		System.out.println("Max: Itr: " + bst.treeMaxItr());
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		Node(int data) {
			this.data = data;
		}
	}

	static class BSTree {
		Node root;

		public void addNode(int data) {
			this.root = add(root, data);
		}

		public Node add(Node temp, int data) {
			if (temp == null) {
				temp = new Node(data);
			} else {
				if (data <= temp.data) {
					temp.left = add(temp.left, data);
				} else {
					temp.right = add(temp.right, data);
				}
			}
			return temp;
		}

		public void inOrder() {
			inOrderTra(root);
			System.out.println();
		}

		public void inOrderNoRec() {

		}

		public void inOrderTra(Node top) {
			if (top != null) {
				inOrderTra(top.left);
				System.out.print(top.data + " -> ");
				inOrderTra(top.right);
			}
		}

		public int treeMinRec() {
			return treeMinRecPri(root);
		}

		public int treeMinRecPri(Node temp) {
			if (temp.left == null) {
				return temp.data;
			} else {
				return treeMinRecPri(temp.left);
			}
		}

		public int treeMinItr() {
			return treeMinItrPri(root);
		}

		public int treeMinItrPri(Node temp) {
			while (temp.left != null) {
				temp = temp.left;
			}
			return temp.data;
		}

		public int treeMaxRec() {
			return treeMaxRecPri(root);
		}

		public int treeMaxRecPri(Node temp) {
			if (temp.right == null) {
				return temp.data;
			} else {
				return treeMaxRecPri(temp.right);
			}
		}

		public int treeMaxItr() {
			return treeMaxItrPri(root);
		}

		public int treeMaxItrPri(Node temp) {
			while (temp.right != null) {
				temp = temp.right;
			}
			return temp.data;
		}
	}
}
