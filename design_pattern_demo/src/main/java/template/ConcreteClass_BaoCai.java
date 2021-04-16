package template;

/**
 * @author wangxing
 * @date 2021/2/27 19:30
 */
public class ConcreteClass_BaoCai extends AbstractClass {
    @Override
    public void pourVegetable() {
        System.out.println("包菜下锅");
    }

    @Override
    public void pourSauce() {
        System.out.println("包菜调料下锅");
    }
}
