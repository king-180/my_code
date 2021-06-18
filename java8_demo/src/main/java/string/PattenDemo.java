package string;

import java.util.regex.Pattern;

/**
 * @author wangxing
 * @date 2021/6/18 14:45
 */
public class PattenDemo {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{8}");
        boolean matches = pattern.matcher("20210618").matches();
        System.out.println(matches);
    }

}
