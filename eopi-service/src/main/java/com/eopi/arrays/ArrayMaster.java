package com.eopi.arrays;

import java.util.Arrays;

public class ArrayMaster {
	/**
	 * Given an array of integers, group all
	 * even numbers odd numbers together.
	 * 
	 * Time complexity: O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] oddEven(int[] arr) {
		int nextEven = 0;
		int nextOdd = arr.length - 1;
		
		while(nextEven < nextOdd) {
			if(arr[nextEven] % 2 == 0) {
				nextEven++;
			} else {
				int temp = arr[nextEven];
				arr[nextEven] = arr[nextOdd];
				arr[nextOdd] = temp;
				nextOdd--;
			}
		}
		return arr;
	}
	
	/**
	 * Given an array with repeated items, sort it.
	 * Solution: 3 way quick sort
	 * 
	 * Time complexity: O(n * log n)
	 *  
	 * @param a
	 * @param lo
	 * @param hi
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void dutchNationalFlagSort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int lt = lo;
		int gt = hi;
		int i = lo;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0)
				swap(a, i++, lt++);
			else if(cmp > 0) 
				swap(a, i, gt--);
			else
				i++;
		}
		dutchNationalFlagSort(a, lo, lt - 1);
		dutchNationalFlagSort(a, gt + 1, hi);
	}
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}
	
	/**
	 * Given an array, add 1 to the array treating it as
	 * a whole number and return the resulting array.
	 * 
	 * Time complexity: O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] plusOne(int[] arr) {
		int carry = 1;
		for(int i = arr.length - 1; i >= 0; i--){
			int val = arr[i] + carry;
			arr[i] = val % 10;
			carry = val / 10;
		}
		if(carry == 1) {
			int[] temp = new int[arr.length + 1];
			temp[0] = 1;
			return temp;
		}
		return arr;
	}
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		int[] oddEven = oddEven(new int[] {2,3,4,5,6,7,8,9});
		System.out.println(Arrays.toString(oddEven));
		
		int[] plusOne = plusOne(new int[] {9,9,9,9});
		System.out.println(Arrays.toString(plusOne));
		
		Comparable[] dutchFlag = new Comparable[] {4,9,4,4,1,9,4,4,9,4,4,1,4};
		dutchNationalFlagSort(dutchFlag, 0, dutchFlag.length - 1);
		System.out.println(Arrays.toString(dutchFlag));
	}
}
