package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 17:28
 */
public class Manager implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println(getClass().getName() + "Manager 收到请求申请 --> request: " + request);
        if (request.getDays() > 3) {
            Request newRequest = new Request.Builder().newRequest(request)
                    .setManagerMsg(request.getName() + "每个月KPI考核不错，可以批准")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, getClass().getName() + "Manager: 早点回去。");
    }
}
