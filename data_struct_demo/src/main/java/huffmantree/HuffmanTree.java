package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
		Node root = createHuffmanTree(arr);

		//测试
		preOrder(root);//
	}

	//编写一个前序遍历方法
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("是空树，不能遍历");
		}
	}

	//创建哈夫曼树的方法
	public static Node createHuffmanTree(int[] arr) {
		//遍历arr数组，将arr的每一个元素构成一个Node,将Node放入ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		//循环处理
		while (nodes.size() > 1) {
			// 从小到大排序
			Collections.sort(nodes);
			// System.out.println("nodes="+nodes);

			// 取出根节点权值最小的两颗二叉树
			Node leftNode = nodes.get(0);// 取出权值最小的结点
			Node rightNode = nodes.get(1);// 取出权值第二小的结点
			// 构建一颗新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			// 从ArrayLis删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// 将parent加入到nodes
			nodes.add(parent);
		}
		return nodes.get(0);
	}

}

//创建结点类
//为了让Node对象支持排序 Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node>{
	int value;//结点权值
	Node left;//指向左子结点
	Node right;

	//写一个前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;//从小到大排序
	}
}