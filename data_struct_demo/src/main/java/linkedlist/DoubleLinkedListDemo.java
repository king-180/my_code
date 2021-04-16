package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "林冲", "豹子头");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "卢俊义", "玉麒麟");

		// 创建链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero3);
		// doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero4);

		// doubleLinkedList.addByOrder(hero3);
		doubleLinkedList.addByOrder(hero2);
		// doubleLinkedList.addByOrder(hero4);
		// doubleLinkedList.addByOrder(hero1);

		// doubleLinkedList.del(2);

		// HeroNode2 newheroNode = new HeroNode2(2, "公孙胜", "入云龙");
		// doubleLinkedList.update(newheroNode);

		// 显示链表
		doubleLinkedList.list();
	}

}

// 定义一个双向链表的类
class DoubleLinkedList {
	private HeroNode2 head = new HeroNode2(0, "", "");

	// 返回头节点
	public HeroNode2 getHead() {
		return head;
	}

	public void add(HeroNode2 heroNode) {
		HeroNode2 tmp = head;
		while (true) {
			if (tmp.next == null) {
				break;
			}
			tmp = tmp.next;
		}
		tmp.next = heroNode;
		heroNode.pre = tmp;
	}

	// 按照编号添加
	public void addByOrder(HeroNode2 heroNode) {
		HeroNode2 tmp = head.next;
		boolean flag = false;
		while (true) {
			if (tmp.next == null) {
				break;
			}
			if (tmp.next.no > heroNode.no) {
				break;
			} else if (tmp.next.no == heroNode.no) {
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			System.out.println("要插入的编号位置节点不存在");
		} else {
			heroNode.next = tmp.next;
			tmp.next = heroNode;
			heroNode.pre = tmp;
		}
	}

	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
		}
		HeroNode2 tmp = head.next;
		while (true) {
			if (tmp == null) {
				break;
			}
			System.out.println(tmp);
			tmp = tmp.next;
		}
	}

	public void del(int no) {
		if (head.next == null) {
			System.out.println("链表为空，不能删除");
			return;
		}
		HeroNode2 tmp = head.next;
		boolean flag = false;
		while (true) {
			if (tmp == null) {
				break;
			}
			if (tmp.no == no) {
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			tmp.pre.next = tmp.next;
			if (tmp.next != null) {// 判断tmp是不是最后一个节点
				tmp.next.pre = tmp.pre;
			}
		} else {
			System.out.println("要删除的节点不存在");
		}
	}

	public void update(HeroNode2 newheroNode) {
		if (head.next == null) {
			System.out.println("链表为空，要更改的节点数据不存在");
			return;
		}
		HeroNode2 tmp = head.next;
		boolean flag = false;
		while (true) {
			if (tmp == null) {
				break;
			}
			if (tmp.no == newheroNode.no) {
				flag = true;
				break;
			}
			tmp = tmp.next;
		}
		if (flag) {
			tmp.name = newheroNode.name;
			tmp.nickname = newheroNode.nickname;
		} else {
			System.out.printf("没有找到编号%d的节点，不能修改", newheroNode.no);
		}
	}
}

// 定义HeroNode2的类，每个HeroNode对象就是一个节点
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;
	public HeroNode2 pre;

	// 构造器
	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	// 为了显示方法，重新toString
	@Override
	public String toString() {
		return "HeroNode[no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}