package queuearray;

import java.util.Scanner;


public class QueueArrayDemo {

	public static void main(String[] args) {
		QueueArray arr = new QueueArray(3);
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
					System.out.println("输出一个数");
					int value = sc.nextInt();
					arr.addQueue(value);
					break;
				case 'g':
					try {
						int res = arr.getQueue();
						System.out.printf("取出的数据是%d\n", res);;
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

class QueueArray {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;

	public QueueArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1;
		rear = -1;
	}
	//队列满
	public boolean isFull() {
		return rear == maxSize-1;
	}
	//队列空
	public boolean isEmpty() {
		return rear == front;
	}
	//添加
	public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列满，无法加入");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	//获取出队
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，不能取");
		}
		front++;
		return arr[front];
	}
	//显示队列所有数据
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列空，没有数据");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}
	//显示队列头
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有数据");
		}
		return arr[front+1];
	}
}