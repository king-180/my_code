package huffmantree.huffmancode;

public class Test {

    public static void main(String[] args) {
        String strByte = "10101000";
        System.out.println((byte) Integer.parseInt(strByte, 2));
    }

}
/* 补码：10101000(符号位：1，表示负数)
 * 反码：10100110(符号位不变，补码 -1)
 * 原码：11011001(符号位不变，然后取反)
 */