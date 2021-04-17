package tree;

/**
 * @author wangxing
 * @date 2021/4/17 13:13
 */
public class RedBlackTreeDemo {

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(3, "c");

        System.out.println("tree size =  " + tree.size());

        System.out.println(tree.get(1));
        System.out.println(tree.get(2));
        System.out.println(tree.get(3));


    }

}

/**
 * 红黑树也是一种自平衡的二叉查找树。
 * 1.每个结点要么是红的要么是黑的。（红或黑）
 * 2.根结点是黑的。（根黑）
 * 3.每个叶结点（叶结点即指树尾端NIL指针或NULL结点）都是黑的。（叶黑）
 * 4.如果一个结点是红的，那么它的两个儿子都是黑的。（红子黑）
 * 5.对于任意结点而言，其到叶结点树尾端NIL指针的每条路径都包含相同数目的黑结点。（路径下黑相同）
 */
class RedBlackTree<K extends Comparable<K>, V> {

    private Node root;
    private int size;
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.right;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColor(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(K key, V value) {
        root = this.put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) {
            size++;
            return new Node(key, value, null, null, RED);
        }
        int tmp = key.compareTo(h.key);
        if (tmp < 0) {
            this.put(h.left, key, value);
        } else if (tmp > 0) {
            this.put(h.right, key, value);
        } else {
            h.value = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            //左旋
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            //右旋
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            //颜色反转
            flipColor(h);
        }
        return h;
    }

    public V get(K key) {
        return this.get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int tmp = key.compareTo(node.key);
        if (tmp < 0) {
            return this.get(node.left, key);
        } else if (tmp > 0) {
            return this.get(node.right, key);
        } else {
            return node.value;
        }
    }

    public int size() {
        return size;
    }
}