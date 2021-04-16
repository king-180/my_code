package combination;

/**
 * @author wangxing
 * @date 2021/2/27 14:16
 */
public class Demo {
    public static void main(String[] args) {

        MenuComponent menu = new Menu("数码科技", 0);

        MenuComponent menu1 = new Menu("数码科技", 1);
        menu1.addMenu(new MenuItem("手机", 2));
        menu1.addMenu(new MenuItem("电脑", 2));
        menu1.addMenu(new MenuItem("相机", 2));


        MenuComponent menu2 = new Menu("智能家具", 1);
        menu2.addMenu(new MenuItem("洗衣机", 2));
        menu2.addMenu(new MenuItem("冰箱", 2));
        menu2.addMenu(new MenuItem("彩电", 2));
        menu2.addMenu(new MenuItem("热水器", 2));

        MenuComponent menu3 = new Menu("服饰装配", 1);
        menu2.addMenu(new MenuItem("衣服", 2));
        menu2.addMenu(new MenuItem("鞋子", 2));

        menu.addMenu(menu1);
        menu.addMenu(menu2);
        menu.addMenu(menu3);

        menu.print();

    }
}
