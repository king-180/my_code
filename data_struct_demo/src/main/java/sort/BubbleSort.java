package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {
//		int arr[] = { 3, 9, -1, 10, -2 };
//		System.out.println("排序前"+ Arrays.toString(arr));
//		bubbleSort(arr);
//		System.out.println("排序后"+ Arrays.toString(arr));
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间：" + date1Str);
		System.out.println(date1);

		bubbleSort(arr);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间：" + date2Str);
/*		int tmp = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j+1]) {
					flag = true;
					tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
			if (flag == false) {
				break;
			} else {
				flag = false;
			}
		}
*/

	}

	public static void bubbleSort(int arr[]) {
		int tmp = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j+1]) {
					flag = true;
					tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
			if (flag == false) {
				break;
			} else {
				flag = false;
			}
		}
	}

}
