/**
 * @author wangxing
 * @date 2020/12/27 9:42
 */
public class HeapSpaceInitialTest {
    public static void main(String[] args) {
        // java虚拟机中堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // java虚拟机中堆内存最大值
        long maxHeapMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xmx:" + maxHeapMemory + "M");
        System.out.println("系统内存：" + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存：" + maxHeapMemory * 4.0 / 1024 + "G");

    }
}
