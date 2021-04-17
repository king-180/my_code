package tree;

/**
 * @author wangxing
 * @date 2021/4/17 13:15
 */
public class LinkBinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        tree.put(1, "张三");
        tree.put(2, "李四");
        tree.put(3, "王五");

        System.out.println("插入完毕后树的: size = " + tree.size());

        System.out.println("key=2 时候对应的元素为 ：" + tree.get(2));

        tree.delete(3);
        System.out.println("删除key=3 后元素个数为: size = " + tree.size());

        // TODO bug ===> 删除后还能获取到key=3
        System.out.println("删除key=3 后元素后，key=3 时候对应的元素为 ：" + tree.get(3));
    }
}

class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    private int size;

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        root = this.put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            // node子树为空
            size++;
            return new Node(key, value, null, null);
        }
        int tmp = key.compareTo(node.key);
        if (tmp > 0) {
            node.right = put(node.right, key, value);
        } else if (tmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        return this.get(root, key);
    }

    public V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int tmp = key.compareTo(node.key);
        if (tmp > 0) {
            return this.get(node.right, key);
        } else if (tmp < 0) {
            return this.get(node.left, key);
        } else {
            return node.value;
        }
    }

    public void delete(K key) {
        this.delete(root, key);
    }

    public Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int tmp = key.compareTo(node.key);
        if (tmp > 0) {
            this.delete(node.right, key);
        } else if (tmp < 0) {
            this.delete(node.left, key);
        } else {
            size--;
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node minNode = node.right;
            while (minNode.left == null) {
                minNode = minNode.left;
            }
            Node n = node.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    n = n.left;
                }
            }
            minNode.left = node.left;
            minNode.right = node.right;
            node = minNode;
        }
        return node;
    }

}