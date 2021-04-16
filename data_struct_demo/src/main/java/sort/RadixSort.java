package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

	public static void main(String[] args) {
		// int[] arr = { 53, 3, 542, 748, 14, 214 };
		// radixSort(arr);
		// System.out.println(Arrays.toString(arr));

		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前：" + date1Str);

		radixSort(arr);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后：" + date2Str);
	}

	// 基数排序
	public static void radixSort(int[] arr) {
		int max = arr[0];// 假设第一个数就是最大数
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		// 得到最大数位数
		int maxlength = (max + "").length();

		// 定义一个二维数组，表示10个桶，每一个桶就是一个一维数组
		int[][] bucket = new int[10][arr.length];
		// 为了记录每个桶实际存放了几个数据，定义一个一维数组来记录每一个桶每一次放入的数据的个数
		int[] bucketElementCounts = new int[10];
		for (int i = 0, n = 1; i < maxlength; i++, n *= 10) {
			for (int j = 0; j < arr.length; j++) {
				// 取出每个元素对应位的值
				int digitOfElement = arr[j] / n % 10;
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			// 按照这个桶的顺序放入原来数组
			int index = 0;
			for (int k = 0; k < bucketElementCounts.length; k++) {
				// 如果桶中有数据，才放入原来数组
				if (bucketElementCounts[k] != 0) {
					// 即第k个桶一维数组
					for (int l = 0; l < bucketElementCounts[k]; l++) {
						arr[index] = bucket[k][l];
						index += 1;
					}
				}
				bucketElementCounts[k] = 0;
			}
		}

		/*
		 * // 第一轮，取个位 for (int j = 0; j < arr.length; j++) { int digitOfElement = arr[j]
		 * % 10; bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
		 * bucketElementCounts[digitOfElement]++; } // 按照这个桶的顺序放入原来数组 int index = 0; for
		 * (int k = 0; k < bucketElementCounts.length; k++) { // 如果桶中有数据，才放入原来数组 if
		 * (bucketElementCounts[k] != 0) { // 即第k个桶一维数组 for (int l = 0; l <
		 * bucketElementCounts[k]; l++) { arr[index] = bucket[k][l]; index += 1; } }
		 * bucketElementCounts[k] = 0; }
		 *
		 * // 第二轮，取十位 for (int j = 0; j < arr.length; j++) { int digitOfElement = arr[j]
		 * / 10 % 10; bucket[digitOfElement][bucketElementCounts[digitOfElement]] =
		 * arr[j]; bucketElementCounts[digitOfElement]++; } // 按照这个桶的顺序放入原来数组 index = 0;
		 * for (int k = 0; k < bucketElementCounts.length; k++) { // 如果桶中有数据，才放入原来数组 if
		 * (bucketElementCounts[k] != 0) { // 即第k个桶一维数组 for (int l = 0; l <
		 * bucketElementCounts[k]; l++) { arr[index] = bucket[k][l]; index += 1; } }
		 * bucketElementCounts[k] = 0; } // 第三轮，取百位 for (int j = 0; j < arr.length; j++)
		 * { int digitOfElement = arr[j] / 100 % 10;
		 * bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
		 * bucketElementCounts[digitOfElement]++; } // 按照这个桶的顺序放入原来数组 index = 0; for
		 * (int k = 0; k < bucketElementCounts.length; k++) { // 如果桶中有数据，才放入原来数组 if
		 * (bucketElementCounts[k] != 0) { // 即第k个桶一维数组 for (int l = 0; l <
		 * bucketElementCounts[k]; l++) { arr[index] = bucket[k][l]; index += 1; } } }
		 */
	}
}
