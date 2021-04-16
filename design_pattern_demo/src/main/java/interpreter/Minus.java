package interpreter;

/**
 * @author wangxing
 * @date 2021/2/28 13:34
 */
public class Minus extends AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public Minus(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }

    @Override
    public String toString() {
        return "( " + left.toString() + " - " + right.toString() + " )";
    }
}
