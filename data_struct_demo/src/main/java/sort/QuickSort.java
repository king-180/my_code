package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {
//		int[] arr = { -9, 78, 0, 23, -567, 70 };
//		quickSort(arr,0,arr.length-1);
//		System.out.println(Arrays.toString(arr));

		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前："+date1Str);

		quickSort(arr, 0, arr.length-1);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后："+date2Str);

	}

	// 快速排序
	public static void quickSort(int[] arr, int left, int right) {
		int l = left;
		int r = right;
		int pivot = arr[(left + right) / 2];
		int tmp = 0;
		while (l < r) {
			while (arr[l] < pivot) {
				l += 1;
			}
			while (arr[r] > pivot) {
				r -=1 ;
			}
			if (l >= r) {
				break;
			}
			tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
			if (arr[l] == pivot) {
				r -= 1;
			}
			if (arr[r] == pivot) {
				l +=1;
			}
		}
		//如果l==r,必须l++, r--,防止栈溢出
		if (l == r) {
			l += 1;
			r -= 1;
		}
		//向左递归
		if (left < r) {
			quickSort(arr, left, r);
		}
		//向右递归
		if (right > l) {
			quickSort(arr, l, right);
		}

	}

}
