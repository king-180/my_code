package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 19:27
 */
public class CustomInterceptor implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println(getClass().getName() + " --> request: " + request);
        if (request != null && "事假".equals(request.getReason())) {
            Request newRequest = new Request.Builder()
                    .newRequest(request)
                    .setCustomInfo(request.getName() + "请的是事假，而且很着急，请领导重视一下！")
                    .build();
            System.out.println(getClass().getName() + " --> 转发请求");
            return chain.proceed(newRequest);
        }
        return new Result(true, "同意请假");
    }
}
