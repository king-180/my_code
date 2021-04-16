package singleton.destroy_singleton.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wangxing
 * @date 2021/2/28 16:49
 */
public class Demo {
    public static void main(String[] args) throws Exception {
//        writeObjectToFile();
        readObjectFromFile();
        readObjectFromFile();
    }

    private static void readObjectFromFile() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\workspace2\\DesignPattern\\src\\com\\wx\\singleton\\destroy\\serializable\\instance.txt"));
        Singleton instance = (Singleton) ois.readObject();
        ois.close();
        System.out.println(instance);
    }

    private static void writeObjectToFile() throws Exception {
        Singleton instance = Singleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\workspace2\\DesignPattern\\src\\com\\wx\\singleton\\destroy\\serializable\\instance.txt"));
        oos.writeObject(instance);
        oos.close();
    }
}
