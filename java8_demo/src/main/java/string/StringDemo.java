package string;

/**
 * @author wangxing
 * @date 2021/5/10 14:35
 */
public class StringDemo {
    public static void main(String[] args) {

        System.out.println("#\u0009####");
        System.out.println("###\u0009####");

        System.out.println("####" + '\u0009' + "####");
        System.out.println("####\u0009####");

        System.out.println("####" + '\t' + "####");
        System.out.println("####\t####");

        System.out.println("####" + "\t" + "####");

        System.out.println("1,2,3".replaceAll(",", ""));
        System.out.println("1,2,3".replace(",", ""));

    }
}
