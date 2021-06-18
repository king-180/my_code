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

        String format = String.format("how are you, " + "%s:%s", "hello", "world");
        System.out.println(format);

        System.out.println("1,2.3;4/5".replaceAll("[,.;/]", ""));

        System.out.println("a...bcdef".substring(0, 3));

    }
}
