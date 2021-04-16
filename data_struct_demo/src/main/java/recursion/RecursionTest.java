package recursion;

public class RecursionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test(4);
		System.out.println(factor(3));
	}

	// 打印问题
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		} //else {
		System.out.printf("n=%d\n", n);
		//}
	}

	// 阶乘问题
	public static int factor(int n) {
		if (n == 1) {
			return 1;
		} else {
			return factor(n - 1) * n;
		}
	}
}
