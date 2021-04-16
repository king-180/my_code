package decorator;

/**
 * @author wangxing
 * @date 2021/2/25 18:53
 */
public class Demo {
    public static void main(String[] args) {

        FastFood fastFood = new FiredRice();

        System.out.println(fastFood.getDesc() + " " + fastFood.cost() + "元");

        System.out.println("===================");

        fastFood = new Egg(fastFood);

        System.out.println(fastFood.getDesc() + " " + fastFood.cost() + "元");

        System.out.println("===================");

        fastFood = new Egg(fastFood);

        fastFood = new Bacon(fastFood);

        System.out.println(fastFood.getDesc() + " " + fastFood.cost() + "元");

        System.out.println("===================");

        FastFood fastFood2 = new FiredNoodles();

        System.out.println(fastFood2.getDesc() + " " + fastFood2.cost() + "元");

        System.out.println("===================");

        fastFood2 = new Bacon(fastFood2);

        System.out.println(fastFood2.getDesc() + " " + fastFood2.cost() + "元");
    }
}
