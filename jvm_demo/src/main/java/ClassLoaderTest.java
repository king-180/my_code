import sun.misc.Launcher;

import java.net.URL;

/**
 * @author wangxing
 * @date 2020/12/25 22:45
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        // 系统类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader ==> " + systemClassLoader);

        // 扩展类加载器 sun.misc.Launcher$ExtClassLoader@1540e19d
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("extClassLoader ==> " + extClassLoader);

        // 引导类加载器 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("bootstrapClassLoader ==> " + bootstrapClassLoader);

        // 用户自定义类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader ==> " + classLoader);

        // String的类加载器 null
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println("stringClassLoader ==> " + stringClassLoader);

        // 获取BootStrap ClassLoader 能加载的类的路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
    }

}
