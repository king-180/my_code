package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {
//		int[] arr = {12, 10, 11, 9};
//		insertSort(arr);
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间："+date1Str);

		insertSort(arr);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间："+date2Str);
	}
	//插入排序
	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex =0;
		for (int i = 1; i < arr.length; i++) {
			insertVal = arr[i];
			insertIndex = i-1;
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex +1] = arr[insertIndex];
				insertIndex--;
			}if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}
		}

	}

}
