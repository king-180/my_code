package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {
		int[] arr = { 1, 8, 10, 89, 74, 123 };
		System.out.println("index=" + fibSearch(arr, 10));
	}

	// 构建斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	// 斐波那契查找算法,要求有序数组(非递归方式)
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int k = 0;// 斐波那契分割数值对应下标
		int mid = 0;
		int f[] = fib();// 获取斐波那契数列
		while (high > f[k] - 1) {// 获取到斐波那契分割数值对应下标
			k++;
		}
		// 因为f[k]可能大于数组a的长度，因此使用Arrays类 构造一个新数组，并指向tmp[]
		// 不足的部分用0填充
		int[] tmp = Arrays.copyOf(a, f[k]);
		// 实际上需求使用a数组最后的数字填充tmp
		for (int i = high + 1; i < tmp.length; i++) {
			tmp[i] = a[high];
		}
		while (low <= high) {// 开始寻找key
			mid = low + f[k - 1] - 1;
			if (key < tmp[mid]) {// 向左边查找
				high = mid - 1;
				// 为什么是k--
				// 1.全部元素=前面元素+后面元素
				// 2.f[k]=f[k-1]+f[k-2]
				// 因为前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
				// 即在f[k-1]前面继续查找k--
				// 即下次循环mid=f[k-1 -1]-1
				k--;
			} else if (key > tmp[mid]) {// 向右边查找
				low = mid + 1;
				// 为什么是k-=2
				// 1.全部元素=前面元素+后面元素
				// 2.f[k]=f[k-1]+f[k-2]
				// 因为前面有f[k-2]个元素，所以可以继续拆分f[k-1]=f[k-3]+f[k-4]
				// 即在f[k-2]前面继续查找k -= 2
				// 即下次循环mid=f[k-1 -2]-1
				k -= 2;
			} else { // 找到
				if (mid <= high) {// 确定返回哪个下标
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
