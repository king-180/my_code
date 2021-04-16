package interpreter;

/**
 * @author wangxing
 * @date 2021/2/28 13:41
 */
public class Demo {
    public static void main(String[] args) {
        Context context = new Context();
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");
        Variable d = new Variable("d");

        context.assign(a, 10);
        context.assign(b, 2);
        context.assign(c, 3);
        context.assign(d, 4);

        AbstractExpression expression = new Minus(a, new Plus(b, new Minus(c, d)));
        int res = expression.interpret(context);
        System.out.println(expression + " = " + res);
    }
}
