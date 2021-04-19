package reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author wangxing
 * @date 2021/4/19 12:07
 */
public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, ClassNotFoundException {

        // 反射前：在 Person类外部不能访问其内部私有属性结构
        Person person = new Person(1, "Tom", 12);
        person.age = 15;
        System.out.println(person);
        person.show();

        System.out.println();

        // 反射后：
        Class<Person> clazz = Person.class;
        Class<? extends Person> clazz2 = person.getClass();
        Class<?> clazz3 = Class.forName("reflection.Person");
        ClassLoader classLoader = Person.class.getClassLoader();
        Class<?> class4 = classLoader.loadClass("reflection.Person");

        // 通过反射 创建 person 对象
        Constructor<Person> constructor = clazz.getConstructor(Integer.class, String.class, Integer.class);
        Person person2 = constructor.newInstance(2, "Jack", 18);
        System.out.println(person2);

        System.out.println();

        Class<Person> clazz5 = Person.class;
        Person person1 = clazz5.newInstance();
        System.out.println(person1);

        System.out.println();

        // 通过反射 调用对象指定的私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person2, "Lisa");
        System.out.println(person2);

        System.out.println();

        // 调用 public 方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(person2);

        System.out.println();

        // 调用私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String invokeReturn = (String) showNation.invoke(person2, "China");
        System.out.println(invokeReturn);

        System.out.println();

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println();

        // 获取类的所有属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }

        System.out.println();

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println();

        // 获取类的所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println();

        // 获取类的父类
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());

        // 获取类的父类的泛型
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass.getTypeName());
//        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
//        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
//        System.out.println(((Class) actualTypeArguments[0]).getName());

        // 获取类的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println(declaredAnnotation);
        }

        // 获取运行时类实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }

        // 获取运行时类所在的包
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);


    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Person {

    private Integer id;
    private String name;
    public Integer age;

    public void show() {
        System.out.println("我是一个人...");
    }

    private String showNation(String nation) {
        System.out.println("国籍是：" + nation);
        return nation;
    }

}