package stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(4);
		String key = "";
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop) {
			System.out.println("show:显示栈");
			System.out.println("push:入栈");
			System.out.println("pop:出栈");
			System.out.println("exit:退出");
			System.out.println("请输入你的选择：");
			key = sc.next();
			switch (key) {
				case "show":
					stack.list();
					break;
				case "push":
					System.out.println("请输入一个数：");
					int value = sc.nextInt();
					stack.push(value);
					break;
				case "pop":
					try {
						int res = stack.pop();
						System.out.printf("出栈的数据是：%d\n", res);
					} catch (Exception e) {
						System.out.println(e.getMessage());				}
					stack.pop();
					break;
				case "exit":
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

class ArrayStack {
	private int maxSize;
	private int[] stack;
	private int top = -1; //栈顶初始化为-1
	//构造器
	public  ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	//栈满
	public boolean isFull() {
		return top == maxSize-1;
	}
	//栈空
	public boolean isEmpty() {
		return top == -1;
	}
	//入栈
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//出栈
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//遍历栈,需要从栈顶显示数据
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空，没有数据");
			return;
		}
		for (int i = top; i > -1; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}
}