package com.clrs.ch12;

public class Exercise_12_2_3 {

	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.addNode(2);
		bst.addNode(5);
		bst.addNode(6);
		bst.addNode(7);
		bst.addNode(1);
		bst.addNode(8);

		bst.inOrder();
		System.out.println("=====");
		System.out.println(" 2 " + bst.search(2).data);
		System.out.println(" 5 " + bst.search(5).data);
		System.out.println(" 6 " + bst.search(6).data);
		System.out.println(" 7 " + bst.search(7).data);
		System.out.println(" 1 " + bst.search(1).data);
		System.out.println(" 8 " + bst.search(8).data);
		System.out.println(" 88 " + bst.search(88));
		System.out.println("=====");

		System.out.println(" 2 then comes " + bst.successor(2).data);
		System.out.println(" 5 then comes " + bst.successor(5).data);
		System.out.println(" 6 then comes " + bst.successor(6).data);
		System.out.println(" 7 then comes " + bst.successor(7).data);
		System.out.println(" 1 then comes " + bst.successor(1).data);
		System.out.println(" 8 then comes " + bst.successor(8));

		System.out.println("=====");

		System.out.println(" 5 then predecessor " + bst.predecessor(5).data);
		System.out.println(" 6 then predecessor " + bst.predecessor(6).data);
		System.out.println(" 7 then predecessor " + bst.predecessor(7).data);
		System.out.println(" 8 then predecessor " + bst.predecessor(8).data);
		System.out.println(" 2 then predecessor " + bst.predecessor(2).data);
		System.out.println(" 1 then predecessor " + bst.predecessor(1));
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

		public Node search(int data) {
			return searchPri(root, data);
		}

		public Node searchPri(Node top, int data) {
			if (top == null || top.data == data) {
				return top;
			}

			if (data <= top.data) {
				return searchPri(top.left, data);
			} else if (data >= top.data) {
				return searchPri(top.right, data);
			}
			return null;
		}

		public Node successor(int data) {
			return successorPri(search(data));
		}

		public Node predecessor(int data) {
			return predecessorPri(search(data));
		}

		public Node minOfTree(Node givenNode) {
			while (givenNode.left != null) {
				givenNode = givenNode.left;
			}
			return givenNode;
		}

		public Node maxOfTree(Node givenNode) {
			while (givenNode.right != null) {
				givenNode = givenNode.right;
			}
			return givenNode;
		}

		public Node successorPri(Node top) {
			if (top.right != null) {
				return minOfTree(top.right);
			} else {
				Node parentNode = top.parent;
				while (parentNode != null && parentNode.right == top) {
					top = parentNode;
					parentNode = parentNode.parent;
				}
				return parentNode;
			}
		}

		public Node predecessorPri(Node top) {
			if (top.left != null) {
				return maxOfTree(top.left);
			} else {
				Node parentNode = top.parent;
				while (parentNode != null && parentNode.left == top) {
					top = parentNode;
					parentNode = parentNode.parent;
				}
				return parentNode;
			}
		}
	}

}
