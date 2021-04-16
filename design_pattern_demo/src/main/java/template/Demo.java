package template;

/**
 * @author wangxing
 * @date 2021/2/27 19:32
 */
public class Demo {
    public static void main(String[] args) {
        ConcreteClass_BaoCai baoCai = new ConcreteClass_BaoCai();
        baoCai.cookProcess();
        System.out.println("================");
        ConcreteClass_CaiXin caiXin = new ConcreteClass_CaiXin();
        caiXin.cookProcess();
    }
}
