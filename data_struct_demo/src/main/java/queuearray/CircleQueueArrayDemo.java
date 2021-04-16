package queuearray;

import java.util.Scanner;

public class CircleQueueArrayDemo {

	public static void main(String[] args) {
		//创建一个环形队列
		CircleArray arr = new CircleArray(4);//设置为4，其最大有效个数为3
		char key = ' ';
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("s(show):显示队列");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):显示队列头数据");
			System.out.println("e(exit):退出程序");
			key = sc.next().charAt(0);
			switch (key) {
				case 's':
					arr.showQueue();
					break;
				case 'a':
					System.out.println("输入一个数");
					int value = sc.nextInt();
					arr.addQueue(value);
					break;
				case 'g':
					try {
						int res = arr.getQueue();
						System.out.printf("取出的数据是%d\n", res);
						;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'h':
					try {
						int res = arr.getQueue();
						System.out.printf("队列的头数据是：%d\n", res);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'e':
					sc.close();
					loop = false;
					break;
				default:
					break;
			}
		}
		System.out.println("程序退出");
	}

}

class CircleArray {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;

	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}

	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}

	public boolean isEmpty() {
		return rear == front;
	}

	public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列满，不能加入数据");
		}
		arr[rear] = n;
		rear = (rear + 1) % maxSize;
	}

	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能取出数据");
		}
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}

	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列空，没有数据");
		}
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// 求出当前队列有效的数据个数
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}

	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有头元素");
		}
		return arr[front];
	}
}