package stream;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wangxing
 * @date 2021/4/19 15:59
 */
public class FlatMapDemo {
    public static void main(String[] args) throws ParseException {
        List<String> list = Arrays.asList("aa", "bb", "ccc", "dddd");
        Stream<Character> characterStream = list.stream().flatMap(FlatMapDemo::formStringToStream);
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> formStringToStream(String s) {
        List<Character> list = new ArrayList<>(10);
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
