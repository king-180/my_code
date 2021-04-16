package linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "林冲", "豹子头");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "卢俊义", "玉麒麟");

		// 创建链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);
		singleLinkedList.add(hero1);
/*
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero2);

		HeroNode newheroNodehero4 = new HeroNode(4, "小卢", "玉麒麟~~");
		singleLinkedList.update(newheroNodehero4);

		singleLinkedList.del(1);

		singleLinkedList.list();

		System.out.printf("除头节点外，有效节点个数为：%d\n",getLength(singleLinkedList.getHead()));

		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
		System.out.println("res=" + res);

*/
		System.out.println("反转前：");
		singleLinkedList.list();
		System.out.println("反转后：");
		reversetList(singleLinkedList.getHead());
		singleLinkedList.list();

	}

	//百度面试题：统计单链表除头节点的节点个数
	public static int getLength(HeroNode head) {
		if (head.next == null) {
			return 0;
		}
		int length = 0;
		//统计除头节点的节点个数
		HeroNode cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}
	//新浪面试题：查找单链表倒数第K个节点
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		if (head.next == null) {
			return null;
		}
		int size = getLength(head);
		if (index <= 0 || index > size) {
			return null;
		}
		HeroNode cur = head.next;
		for (int i = 0; i < size-index; i++) {
			cur = cur.next;
		}
		return cur;
	}
	//腾讯面试题：将单链表反转输出
	public static void reversetList(HeroNode head) {
		if (head.next == null || head.next.next == null) {
			return ;
		}
		HeroNode cur = head.next;
		HeroNode next = null; //指向当前节点的下一个节点
		HeroNode reverseHead = new HeroNode(0, "", "");
		//从头遍历原来的链表，每遍历一个节点用头插法放在新链表上
		while (cur != null) {
			next = cur.next;
			cur.next = reverseHead.next;//将cur的下一个节点指向新链表的最前端
			reverseHead.next = cur;//将cur连接到新链表上
			cur = next;
		}
		//将head.next指向 新链表的reverseHead.next，实现单链表的反转
		head.next = reverseHead.next;
	}

}


// 定义一个单向链表SingleLinkedList类管理我们的英雄
class SingleLinkedList {
	private HeroNode head = new HeroNode(0, "", "");

	//返回头节点
	public HeroNode getHead() {
		return head;
	}

	public void add(HeroNode heroNode) {
		// 头节点不能动,遍历链表,借助辅助指针tmp
		HeroNode tmp = head;
		while (true) {
			if (tmp.next == null) {
				break;
			}
			// 没找到就将tmp后移
			tmp = tmp.next;
		}
		// 找到尾节点
		tmp.next = heroNode;
	}
	//按照编号的顺序插入
	public void addByOrder(HeroNode heroNode) {
		HeroNode tmp = head;
		// tmp位于将要添加的位置的前一个节点
		boolean flag = false;
		// flag标志要添加的位置是否存在
		while (true) {
			if (tmp.next == null) {
				break;
			}
			if (tmp.next.no > heroNode.no) {
				// 位置找到，在tmp的后面
				break;
			} else if (tmp.next.no == heroNode.no) {
				// 说明要添加的位置编号已经被占用
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
		} else {
			heroNode.next = tmp.next;
			tmp.next = heroNode;
		}
	}
	//更新修改单链表的一个节点
	public void update(HeroNode newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode tmp = head;
		boolean flag = false;
		while (true) {
			if (tmp == null) {
				break;
			}
			if (tmp.no == newHeroNode.no) {
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			tmp.name = newHeroNode.name;
			tmp.nickname = newHeroNode.nickname;
		} else {
			System.out.printf("没有找到编号%d的节点，不能修改", newHeroNode.no);
		}
	}
	//删除指定值对应的节点
	public void del(int no) {
		HeroNode tmp = head;
		boolean flag = false;
		while (true) {
			if (tmp.next == null) {
				break;
			}
			if (tmp.next.no == no) {
				//找到待删除编号的前一个节点
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			tmp.next = tmp.next.next;
		} else {
			System.out.printf("要删除的%d节点不存在\n", no);
		}
	}

	//显示单链表的内容
	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode tmp = head.next;
		while (true) {
			// 判断tmp是否到了尾节点
			if (tmp == null) {
				break;
			}
			System.out.println(tmp);
			// 将tmp后移（必须）
			tmp = tmp.next;
		}
	}
}
//定义一个HeroNode的类，每一个HeroNode对象就是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;
	public HeroNode pre;

	// 构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	// 为了显示方法，重新toString
	@Override
	public String toString() {
		return "HeroNade[no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}