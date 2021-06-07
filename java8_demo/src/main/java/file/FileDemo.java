package file;

import java.io.*;

/**
 * @author wangxing
 * @date 2021/5/10 9:51
 */
public class FileDemo {

    public static void main(String[] args) throws Exception {
//        System.out.println(System.getProperty("java.io.tmpdir"));
//        // D:\workspace\my_code\java8_demo\src\main\resources\certs\shelldev\apiclient_cert.1525661911.p12
//        File file = new File("certs/shelldev/apiclient_cert.1525661911.p12");
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getPath());
//        System.out.println(file.getParent());
//        System.out.println(file.getName());
//        String path = Thread.currentThread().getContextClassLoader().getResource("certs/shelldev/apiclient_cert.1525661911.p12").getPath();
//        System.out.println(path);
//
//        File file2 = new File("java8_demo/src/main/resources/certs/shelldev/test.txt");
//        System.out.println(file2.getAbsolutePath());

        String path2 = Thread.currentThread().getContextClassLoader().getResource("certs/shelldev/test.txt").getPath();
        System.out.println(path2);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2.getAbsolutePath())));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path2)));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            System.out.println(readLine);
        }
    }

}
