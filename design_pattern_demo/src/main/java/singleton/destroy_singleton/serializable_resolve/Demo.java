package singleton.destroy_singleton.serializable_resolve;

import singleton.destroy_singleton.serializable.Singleton;

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
        writeObjectToFile();
        readObjectFromFile();
        readObjectFromFile();
    }

    private static void readObjectFromFile() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\workspace2\\DesignPattern\\src\\com\\wx\\singleton\\destroy\\serializable_resolve\\instance.txt"));
        singleton.destroy_singleton.serializable.Singleton instance = (singleton.destroy_singleton.serializable.Singleton) ois.readObject();
        ois.close();
        System.out.println(instance);
    }

    private static void writeObjectToFile() throws Exception {
        singleton.destroy_singleton.serializable.Singleton instance = Singleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\workspace2\\DesignPattern\\src\\com\\wx\\singleton\\destroy\\serializable_resolve\\instance.txt"));
        oos.writeObject(instance);
        oos.close();
    }
}
