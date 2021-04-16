package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { 1, 8, 10, 20, 74,74,74,74, 123 };
		List<Integer> resIndex = binarySearch2(arr, 0, arr.length - 1, 74);
		System.out.println("resIndex=" + resIndex);
	}

	// 二分查找,数组必须有序
	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		if (left>right) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (findVal > midVal) {// 向右递归
			return binarySearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {// 向左递归
			return binarySearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}
	}
	//完成一个课后思考题:
	/*
	 * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
	 * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
	 *
	 * 思路分析
	 * 1. 在找到mid 索引值，不要马上返回
	 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
	 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
	 * 4. 将Arraylist返回
	 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
		if (left>right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (findVal > midVal) {// 向右递归
			return binarySearch2(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {// 向左递归
			return binarySearch2(arr, left, mid - 1, findVal);
		} else {
//			* 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回
			List<Integer> resIndexList = new ArrayList<Integer>();
			//向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			int tmp = mid -1;
			while (true) {
				if (tmp < 0 || arr[tmp] != findVal) {
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexList.add(tmp);
				tmp -= 1;//temp左移
			}
			resIndexList.add(mid);
			//向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			tmp = mid+1;
			while (true) {
				if (tmp > arr.length -1 || arr[tmp] != findVal) {
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexList.add(tmp);
				tmp +=1;//tmp右移
			}
			return resIndexList;
		}
	}

}
