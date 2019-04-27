package com.clrs.ch12;

public class Exercise_12_3_3 {

	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.addNode(2);
		bst.addNode(5);
		bst.addNode(6);
		bst.addNode(7);
		bst.addNode(1);
		bst.addNode(8);

		bst.inOrder();
	}

	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;

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
					Node x = insert(top.left, data);
					x.parent = top;
					top.left = x;
				} else if (data >= top.data) {
					Node x = insert(top.right, data);
					x.parent = top;
					top.right = x;
				}
				return top;
			}
		}

		public void inOrder() {
			inOrderTra(root);
			System.out.println();
		}

		public void inOrderTra(Node top) {
			if (top != null) {
				inOrderTra(top.left);
				System.out.print(top.data + "[" + (top.parent != null ? "" + top.parent.data : "*") + "] => ");
				inOrderTra(top.right);
			}
		}
	}
}
