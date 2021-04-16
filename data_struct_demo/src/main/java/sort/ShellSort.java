package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {
		// int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		// shellSort(arr);
		// System.out.println("排序后：" + Arrays.toString(arr));

		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间：" + date1Str);

		shellSort2(arr);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间：" + date2Str);
	}

	// 交换法希尔排序
	public static void shellSort(int[] arr) {
		int tmp = 0;
		int count = 0;
		for (int gep = arr.length / 2; gep > 0; gep /= 2) {
			for (int i = gep; i < arr.length; i++) {
				for (int j = i - gep; j >= 0; j -= gep) {
					if (arr[j] > arr[j + gep]) {
						tmp = arr[j];
						arr[j] = arr[j + gep];
						arr[j + gep] = tmp;
					}
				}
			}
			// System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
		}

		/*
		 * //希尔排序第一轮，10个数据分成5组 for (int i = 5; i < arr.length; i++) { for (int j = i -
		 * 5; j >= 0; j -= 5) { if (arr[j] > arr[j + 5]) { tmp = arr[j]; arr[j] = arr[j
		 * + 5]; arr[j + 5] = tmp; } } } //希尔排序第二轮，10个数据分成5/2 = 2组 for (int i = 2; i <
		 * arr.length; i++) { for (int j = i - 2; j >= 0; j -= 2) { if (arr[j] > arr[j +
		 * 2]) { tmp = arr[j]; arr[j] = arr[j + 2]; arr[j + 2] = tmp; } } }
		 * //希尔排序第三轮，10个数据分成5/2 = 2/2 = 1组 for (int i = 1; i < arr.length; i++) { for
		 * (int j = i - 1; j >= 0; j -= 1) { if (arr[j] > arr[j + 1]) { tmp = arr[j];
		 * arr[j] = arr[j + 1]; arr[j + 1] = tmp; } } }
		 */
	}

	// 移位法希尔排序
	public static void shellSort2(int[] arr) {
		int tmp = 0;
		for (int gep = arr.length / 2; gep > 0; gep /= 2) {
			for (int i = gep; i < arr.length; i++) {
				// for (int j = i - gep; j >= 0; j -= gep) {
				// if (arr[j] > arr[j + gep]) {
				// tmp = arr[j];
				// arr[j] = arr[j + gep];
				// arr[j + gep] = tmp;
				// }
				// }
				int j = i;
				if (arr[j] < arr[j - gep]) {
					while (j - gep >= 0 && tmp < arr[j - gep]) {
						arr[j] = arr[j - gep];
						j -= gep;
					}
					arr[j] = tmp;
				}
			}
			// System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
		}
	}
}
