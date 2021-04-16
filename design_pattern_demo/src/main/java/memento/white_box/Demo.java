package memento.white_box;

/**
 * @author wangxing
 * @date 2021/2/28 13:04
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("-------------大战boss前----------------");
        GameRole gameRole = new GameRole();
        gameRole.initState();
        gameRole.showState();
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.saveState());

        System.out.println("-------------大战boss后----------------");
        gameRole.fight();
        gameRole.showState();

        System.out.println("-------------恢复之前状态----------------");
        gameRole.recoverState(roleStateCaretaker.getRoleStateMemento());
        gameRole.showState();
    }
}
