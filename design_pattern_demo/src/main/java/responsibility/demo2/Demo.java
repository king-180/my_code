package responsibility.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/30 17:45
 */
public class Demo {
    public static void main(String[] args) {
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Request request = new Request.Builder()
                .setName("张三")
                .setDays(5)
                .setReason("事假")
                .build();

        Result result = client.execute(request);
        System.out.println("请假结果：" + result);

    }
}

class ChainOfResponsibilityClient {

    private final List<Ratify> ratifyList;

    public ChainOfResponsibilityClient() {
        this.ratifyList = new ArrayList<>();
    }

    public void addRatify(Ratify ratify) {
        this.ratifyList.add(ratify);
    }

    public Result execute(Request request) {
        List<Ratify> ratifies = new ArrayList<>();
        ratifies.addAll(ratifyList);
        ratifies.add(new GroupLeader());
        ratifies.add(new Manager());
        ratifies.add(new DepartmentHeader());

        RealChain realChain = new RealChain(request, ratifies, 0);
        return realChain.proceed(request);
    }
}