package com.clrs.ch32;

public class RabinKarpSearch {

	public static void main(String[] args) {
		System.out.println(pattSearch("abcdefgh".toCharArray(), "cde".toCharArray()));
	}

	public final static long prime = 3;

	public static int pattSearch(char[] text, char[] pattern) {
		int n = text.length;
		int m = pattern.length;

		long patternHash = createHash(pattern, m - 1);
		long textHash = createHash(text, m - 1);

		for (int i = 1; i < n - m + 1; i++) {
			if (patternHash == textHash && regionMatch(text, i - 1, i + m - 2, pattern, 0, m - 1)) {
				return i - 1;
			}
			if (i <= n - m + 1) {
				textHash = newHashValue(text, i - 1, i + m - 1, textHash, m);
			}

		}
		return -1;
	}

	private static long newHashValue(char[] str, int oldIndex, int newIndex, long oldHash, int patternLen) {
		long newHash = oldHash - str[oldIndex];
		newHash = newHash / prime;
		newHash += str[newIndex] * Math.pow(prime, patternLen - 1);
		return newHash;
	}

	public static long createHash(char[] str, int until) {
		long hash = 0;
		for (int i = 0; i <= until; i++) {
			hash += str[i] * Math.pow(prime, i);
		}
		return hash;
	}

	public static boolean regionMatch(char[] str1, int s1, int e1, char[] str2, int s2, int e2) {
		if (e1 - s1 != e2 - s2) {
			return false;
		} else {
			while (s1 != e1 && s2 != e2) {
				if (str1[s1] != str2[s2]) {
					return false;
				}
				s1++;
				s2++;
			}
		}
		return true;
	}
}
