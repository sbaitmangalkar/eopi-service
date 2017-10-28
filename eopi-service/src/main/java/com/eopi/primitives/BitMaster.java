package com.eopi.primitives;

public class BitMaster {

	/**
	 * Time Complexity = O(n)
	 * 
	 * @param n
	 * @return
	 */
	public short countNumberOfSetBits(int n) {
		short setBitCount = 0;
		while (n != 0) {
			setBitCount += (n & 1);
			n = n >>> 1;
		}
		return setBitCount;
	}

	/**
	 * Parity of binary word - is 1 if the number of 1's in word is odd else 0.
	 * 
	 * Check each binary digit and keep track of number mod 2
	 * 
	 * @param n
	 * @return
	 */
	public short checkParityBruteForce(long n) {
		short result = 0;
		while (n != 0) {
			result ^= (n & 1);
			n = n >>> 1;
		}
		return result;
	}

	/**
	 * n & (n - 1) method. Time complexity = O(k) where k is number of 1's in
	 * input word
	 * 
	 * @param n
	 * @return
	 */
	public short checkParityRemovingLowestSetBit(long n) {
		short result = 0;
		while (n != 0) {
			result = (short) (result ^ 1);
			n = n & (n - 1);
		}
		return result;
	}

	/**
	 * XOR is associative [a + (b + c) == (a + b) + c] and commutative [a + b ==
	 * b + c] To get parity of 64 bit word, group it into two 32 bit words and
	 * then perform XOR of these two 32 bit words.
	 * 
	 * XOR of any two 32 bit values can be computed with a single shift and
	 * single 32 bit XOR instruction.
	 * 
	 * Time complexity = O(log n)
	 * 
	 * @param n
	 * @return
	 */
	public short checkParityXORProperty(long n) {
		n = n ^ n >>> 32;
		n = n ^ n >>> 16;
		n = n ^ n >>> 8;
		n = n ^ n >>> 4;
		n = n ^ n >>> 2;
		n = n ^ n >>> 1;

		return (short) (n & 0x1);
	}

	/**
	 * Extract ith and jth bit. Check if they are different. If different, flip
	 * them.
	 *
	 * Time complexity = O(1)
	 * 
	 * @param n
	 * @param i
	 * @param j
	 * @return
	 */
	public long swapBits(long n, int i, int j) {
		// Extracting ith and jth bits
		if (((n >>> i) & 1) != ((n >>> j) & 1)) {
			long bitMask = (1L << i) | (1L << j);
			n = n ^ bitMask;
		}
		return n;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public long reverseBits(long n) {
		for (int i = 0; i < 32; i++) {
			n = swapBits(n, i, 32 - i - 1);
		}
		return n;
	}

	/**
	 * Start from LSB. Swap LSB with its rightmost bit that differs from it.
	 * 
	 * Solution: Flip bits at k1 and k2 where k1 > k2. Then difference between
	 * the original integer and new integer is Math.pow(2, k1) - Math.pow(2,
	 * k2).
	 * 
	 * To minimize this, make k1 as small as possible and k2 as close to k1. To
	 * preserve weights bit at k1 and k2 should be different.
	 * 
	 * k1 = Rightmost bit that's different for LSB k2 = Very next bit.
	 * 
	 * Upshot = Swap the two right most consequtive bits that are different.
	 * 
	 * Time Complexity = O(n)
	 * 
	 * @param n
	 * @return
	 */
	public long closestIntSameWeight(long n) {
		for (int i = 0; i < 62; i++) {
			if (((n >>> i) & 1) != ((n >>> i + 1) & 1)) {
				n = n ^ ((1L << i) | (1L << i + 1));
				break;
			}
		}
		return n;
	}

	/**
	 * Logic: Initialize result to 0. Iterate through bits of x, add 2^k of y to
	 * result if kth bit of y is 1
	 * 
	 * Time complexity = O(n pow 2)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public long multiply(long x, long y) {
		long result = 0;
		while (x != 0) {
			if ((x & 1) != 0) {
				result = add(result, y);
			}
			x = x >>> 1;
			y = y << 1;
		}
		return result;
	}

	/**
	 * 
	 * Used by <code>multiply(long x, long y)</code>
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private long add(long x, long y) {
		long result = x ^ y; // + without carry 0+0=0, 0+1=1+0=1, 1+1=0
		long carry = (x & y) << 1; // 1+1=2
		if (carry != 0) {
			return add(result, carry);
		}
		return result;
	}

	public long reverse(long x) {
		long result = 0;
		long absoluteX = Math.abs(x);
		while (absoluteX != 0) {
			result = result * 10 + absoluteX % 10;
			absoluteX = absoluteX / 10;
		}

		return x < 0 ? -result : result;
	}

	public static void main(String[] args) {
		BitMaster bitMaster = new BitMaster();
		short setBits = bitMaster.countNumberOfSetBits(13);
		System.out.println("Set Bits " + setBits);

		short parityBruteForce = bitMaster.checkParityBruteForce(13);
		System.out.println("Parity Bruteforce " + parityBruteForce);

		short parityRemovingLowestSetBit = bitMaster.checkParityRemovingLowestSetBit(13);
		System.out.println("Parity Removing lowest set bit " + parityRemovingLowestSetBit);

		short parityXORProperty = bitMaster.checkParityXORProperty(13);
		System.out.println("Parity XOR property " + parityXORProperty);

		long reverseBits = bitMaster.reverseBits(13);
		System.out.println("Bit Reversal " + reverseBits);

		long closestIntSameWeight = bitMaster.closestIntSameWeight(11);
		System.out.println("Closest Int with same weight " + closestIntSameWeight);

		long multipliedResult = bitMaster.multiply(10, 20);
		System.out.println("Multiplied result " + multipliedResult);

		long rev = bitMaster.reverse(-24);
		System.out.println("Reversed " + rev);
	}

}
