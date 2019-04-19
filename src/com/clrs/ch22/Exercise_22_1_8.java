package com.clrs.ch22;

public class Exercise_22_1_8 {

	public static void main(String[] args) {
		System.out.println("since its hashtable the lookup time is O(1) ");
		System.out.println("however to find out whether an edge is available or not");
		System.out.println(" we need O(v) time. If we sort all the entries in each ");
		System.out.println("adj list then lookup time can be decrased by using binary search");
		System.out.println("so worst case becomes O(log v)");
	}
}