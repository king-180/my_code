package mediator;

/**
 * @author wangxing
 * @date 2021/2/28 11:47
 */
public class Demo {
    public static void main(String[] args) {
        MediatorStructure mediator = new MediatorStructure();
        Tenant tom = new Tenant("Tom", mediator);
        Tenant jack = new Tenant("Jack", mediator);
        HouseOwner houseOwner = new HouseOwner("包租婆", mediator);
        mediator.setTenant(tom);
        mediator.setHouseOwner(houseOwner);
        tom.constact("我要住房子...");
        houseOwner.constact("3000一个月，租吗...");

    }
}
