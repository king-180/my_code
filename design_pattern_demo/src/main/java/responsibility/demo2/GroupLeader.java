package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 17:28
 */
public class GroupLeader implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("GroupLeader 收到请求申请 --> request: " + request);
        if (request.getDays() > 1) {
            Request newRequest = new Request.Builder()
                    .newRequest(request)
                    .setManagerInfo(request.getName() + "平时表现不错，现在项目也不忙。")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, "Manager: 早点把事情办完，项目离不开你。");
    }
}
