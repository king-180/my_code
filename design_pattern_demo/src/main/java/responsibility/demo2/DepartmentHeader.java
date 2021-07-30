package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 17:29
 */
public class DepartmentHeader implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("DepartmentHeader 收到请求申请 --> request: " + request);
        if (request.getDays() > 7) {
            return new Result(false, "请假超过 7 天");
        }
        return new Result(true, "DepartmentHeader：不要着急，把事情处理完再回来！");
    }
}
