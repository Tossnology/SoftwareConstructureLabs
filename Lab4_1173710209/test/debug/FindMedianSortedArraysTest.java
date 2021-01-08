package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindMedianSortedArraysTest {

	@Test
	void test1() {
		FindMedianSortedArrays test = new FindMedianSortedArrays();
		int[] nums1 =  {1,3};
		int[] nums2 =  {2};
		
		assertEquals(test.findMedianSortedArrays(nums1, nums2),2);
	}

	@Test
	void test2() {
		FindMedianSortedArrays test = new FindMedianSortedArrays();
		int[] nums1 =  {1,2};
		int[] nums2 =  {3,4};
		
		assertEquals(test.findMedianSortedArrays(nums1, nums2),2.5);
	}
	
	@Test
	void test3() {
		FindMedianSortedArrays test = new FindMedianSortedArrays();
		int[] nums1 =  {1,1,1};
		int[] nums2 =  {5,6,7};
		
		assertEquals(test.findMedianSortedArrays(nums1, nums2),3);
	}
	
	@Test
	void test4() {
		FindMedianSortedArrays test = new FindMedianSortedArrays();
		int[] nums1 =  {1,1};
		int[] nums2 =  {1,2,3};
		
		assertEquals(test.findMedianSortedArrays(nums1, nums2),1);
	}
}
