package com.clrs.ch12;

public class Exercise_12_1_3 {

	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.addNode(2);
		bst.addNode(5);
		bst.addNode(6);
		bst.addNode(7);
		bst.addNode(1);
		bst.addNode(8);

		bst.inOrder();
		bst.inOrderNoRec();
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
			root = insert(root, data);
		}

		public Node insert(Node top, int data) {
			if (top == null) {
				return new Node(data);
			} else {
				if (data < top.data) {
					top.left = insert(top.left, data);
				} else if (data >= top.data) {
					top.right = insert(top.right, data);
				}
				return top;
			}
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

	}
}
