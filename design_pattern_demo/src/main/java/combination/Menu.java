package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/2/27 14:01
 */
public class Menu extends MenuComponent {

    private List<MenuComponent> menuComponentList = new ArrayList<>(10);

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void addMenu(MenuComponent menuComponent) {
        menuComponentList.add(menuComponent);
    }

    @Override
    public void removeMenu(MenuComponent menuComponent) {
        menuComponentList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuComponentList.get(index);
    }

    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(level + " --> " + name);
        for (MenuComponent component : menuComponentList) {
            component.print();
        }
    }

}
