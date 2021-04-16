package template;

/**
 * @author wangxing
 * @date 2021/2/27 19:30
 */
public class ConcreteClass_CaiXin extends AbstractClass {
    @Override
    public void pourVegetable() {
        System.out.println("菜心下锅");
    }

    @Override
    public void pourSauce() {
        System.out.println("菜心调料下锅");
    }
}
