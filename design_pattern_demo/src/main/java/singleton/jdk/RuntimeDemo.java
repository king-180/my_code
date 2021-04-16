package singleton.jdk;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangxing
 * @date 2021/2/28 18:14
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ipconfig");
        InputStream is = process.getInputStream();
        byte[] bytes = new byte[1024 * 1024 * 100];
        int len = is.read(bytes);
        System.out.println(new String(bytes, 0, len, "GBK"));
    }
}
