package combination;

/**
 * @author wangxing
 * @date 2021/2/27 13:56
 */
public abstract class MenuComponent {

    protected String name;
    protected int level;

    public void addMenu(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void removeMenu(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public abstract void print();
}
