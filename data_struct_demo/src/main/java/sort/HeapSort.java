package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
//		int arr[] = { 4, 6, 8, 5, 9 , -1, 90};
		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*80000000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间：" + date1Str);

		heapSort(arr);

		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间：" + date2Str);
	}

	//编写堆排序方法
	public static void heapSort(int[] arr) {
		System.out.println("堆排序!");
		int tmp = 0;
		//分步完成
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("第一次"+Arrays.toString(arr));
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("第二次"+Arrays.toString(arr));

		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		for (int j = arr.length - 1; j > 0; j--) {
			tmp = arr[j];
			arr[j] = arr[0];
			arr[0] = tmp;
			adjustHeap(arr, 0, j);
		}
//		System.out.println("数组=" + Arrays.toString(arr));
	}

	//将一个数组（二叉树）调整成一个大顶堆
	public static void adjustHeap(int[] arr, int i,int length) {
		//arr[]表示待调整的数组
		//i表示非叶子结点在数组中的索引
		//length表示对多少个元素继续调整，length逐渐减小
		int tmp = arr[i];//先取出当前元素的值保存在临时变量tmp中
		//开始调整
		//k是i结点的左子节点
		for (int k = i*2+1; k < length; k = k*2+1) {
			if (k+1 < length && arr[k] < arr[k+1]) {//说明左子节点的值小于右子节点的值
				k++;//k指向右子节点
			}
			if (arr[k] > tmp) {
				arr[i] = arr[k];//把较大的值赋给当前节点
				i = k;//让i指向k,继续循环比较
			} else {
				break;
			}
		}
		//当for循环结束后，已经将以i为父节点的树的最大值  放在了最顶上（局部）
		arr[i] = tmp;//将tmp赋值到调整后的位置

	}

}
