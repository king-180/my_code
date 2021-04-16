package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {
//		int[] arr = { 8, 4, 5, 7, 1, 3, 6, 2 };
//		int[] tmp = new int[arr.length];
//		mergeSort(arr, 0, arr.length-1, tmp);
//		System.out.println(Arrays.toString(arr));

		int[] arr = new int[80000];
		int[] tmp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前："+date1Str);

		mergeSort(arr, 0, arr.length-1, tmp);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后："+date2Str);
	}

	// 分+合方法
	public static void mergeSort(int[] arr, int left, int right, int[] tmp) {
		if (left < right) {
			int mid = (left + right) / 2;
			// 向左递归分解
			mergeSort(arr, left, mid, tmp);
			// 向右递归分解
			mergeSort(arr, mid + 1, right, tmp);
			//到合并
			merge(arr, left, mid, right, tmp);
		}
	}

	// 合并方法
	public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		// 先把左右两边的数据（有序）按照规则填充到tmp数组，直到左右两边有一边处理完毕为止
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				tmp[t] = arr[i];// 将左边的数据填充到tmp数组
				t += 1;
				i += 1;
			} else {
				tmp[t] = arr[j];// 将右边的数据填充到tmp数组
				t += 1;
				j += 1;
			}
		}
		// 把有剩余数据的一边全部填充到tmp数组
		while (i <= mid) {// 左边剩余
			tmp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while (j <= right) {// 右边剩余
			tmp[t] = arr[j];
			t += 1;
			j += 1;
		}
		// 将tmp数组的元素拷贝到arr,并不是每一次都是拷贝所有
		t = 0;
		int tmpleft = left;
		//System.out.println("tmpleft="+tmpleft+" "+"right="+right);
		while (tmpleft <= right) { // 第一次合并tmpleft=0,right=1;(2,3)(0,3)(0,7)
			arr[tmpleft] = tmp[t];
			t += 1;
			tmpleft += 1;
		}
	}
}
