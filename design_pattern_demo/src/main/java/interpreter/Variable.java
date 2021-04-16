package interpreter;

/**
 * @author wangxing
 * @date 2021/2/28 13:33
 */
public class Variable extends AbstractExpression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Context context) {
        return context.getVal(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
