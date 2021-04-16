package combination;

/**
 * @author wangxing
 * @date 2021/2/27 14:14
 */
public class MenuItem extends MenuComponent {

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(level + " --> " + name);
    }
}
