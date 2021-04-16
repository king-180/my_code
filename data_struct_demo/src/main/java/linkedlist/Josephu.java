package linkedlist;

public class Josephu {

	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(7);
		circleSingleLinkedList.showBoy();

		circleSingleLinkedList.countBoy(1, 3, 7);
	}

}

class CircleSingleLinkedList {
	private BoyNode first = null;

	public void addBoy(int nums) {
		if (nums < 1) {
			System.out.println("nums的数值不正确");
			return;
		}
		// 创建辅助指针,帮助构建环形链表
		BoyNode curBoy = null;
		for (int i = 1; i <= nums; i++) {
			BoyNode boy = new BoyNode(i);
			if (i == 1) {
				first = boy;
				first.setNext(first);
				curBoy = first;
			} else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}

	// 遍历
	public void showBoy() {
		if (first == null) {
			System.out.println("环形链表为空");
			return;
		}
		BoyNode curBoy = first;
		while (true) {
			System.out.printf("小孩的编号为%d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {
				break;
			}
			curBoy = curBoy.getNext();
		}
	}

	// 打印出圈顺序
	public void countBoy(int startNo, int countNum, int nums) {
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误");
			return;
		}
		BoyNode helper = first;
		while (true) {
			if (helper.getNext() == first) {
				break;
			}
			helper = helper.getNext();
		}
		//报数前先让first和helper移动到将要开始报数的地方，移动k-1次
		for (int i = 0; i < startNo-1; i++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//报数时 让first和helper同时移动m-1次，然后出圈
		while (true) {
			if (helper == first) {
				//说明圈中只剩一人
				break;
			}
			for (int i = 0; i < countNum-1; i++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//这时候first指向要出圈的小孩节点
			System.out.printf("出圈小孩的编号为%d\n", first.getNo());
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.printf("留在圈中的小孩编号为%d\n", helper.getNo());
	}
}

class BoyNode {
	private int no;
	private BoyNode next;

	public BoyNode(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public BoyNode getNext() {
		return next;
	}

	public void setNext(BoyNode next) {
		this.next = next;
	}

}