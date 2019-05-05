package com.clrs.ch32;

public class Exercise_32_1_2 {
	public static void main(String[] args) throws Exception {
		System.out.println(nativeStringMatch("abcdefgh", "efgh"));

	}

	public static int nativeStringMatch(String t, String p) {
		char[] tc = t.toCharArray();
		char[] pc = p.toCharArray();
		for (int shift = 0; shift <= tc.length - pc.length; shift++) { // n-m+1 times
			int currentCounter = 0;
			for (; currentCounter < pc.length;) {
				if (tc[shift + currentCounter] == pc[currentCounter]) {
					currentCounter++;
				} else {
					break;
				}
			}
			if (currentCounter == pc.length) {
				return shift;
			}
		}
		return -1;
	}
}
