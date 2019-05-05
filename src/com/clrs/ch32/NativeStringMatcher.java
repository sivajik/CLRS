package com.clrs.ch32;

public class NativeStringMatcher {

	public static void main(String[] args) {
		System.out.println(nativeStringMatch("acacatdonkey", "ey"));
	}

	public static int nativeStringMatch(String t, String p) {
		char[] tc = t.toCharArray();
		char[] pc = p.toCharArray();
		for (int shift = 0; shift <= tc.length - pc.length; shift++) { // n-m+1 times
			if (areTheySame(tc, pc, shift)) { // m times
				return shift;
			}
		}
		return -1;
	}

	public static boolean areTheySame(char[] tc, char[] pc, int startPos) {
		for (int i = 0; i < pc.length; i++) {
			if (pc[i] != tc[i + startPos]) {
				return false;
			}
		}
		return true;
	}
}
