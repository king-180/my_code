package search;

public class OrderSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 74, 123};
        int index = orderSearch(arr, 89);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为：" + index);
        }
    }

    //线性查找算法
    public static int orderSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
