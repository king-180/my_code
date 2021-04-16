package memento.black_box;

/**
 * @author wangxing
 * @date 2021/2/28 13:03
 */
public class RoleStateCaretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
