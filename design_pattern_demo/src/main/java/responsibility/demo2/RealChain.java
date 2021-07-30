package responsibility.demo2;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/30 17:19
 */
public class RealChain implements Ratify.Chain {

    /**
     * 具体的请求Request实例
     */
    private Request request;
    /**
     * Ratify接口的实现类集合
     */
    private List<Ratify> ratifyList;
    /**
     * 已经处理过该request的责任人数量
     */
    private int count;

    public RealChain(Request request, List<Ratify> ratifyList, int count) {
        this.request = request;
        this.ratifyList = ratifyList;
        this.count = count;
    }

    /**
     * 返回当前Request对象或者返回当前进行包装后的Request对象
     *
     * @return 返回当前Request对象或者返回当前进行包装后的Request对象
     */
    @Override
    public Request request() {
        return request;
    }

    /**
     * 具体转发功能
     *
     * @param request 当前请求
     * @return 转发结果
     */
    @Override
    public Result proceed(Request request) {
        Result proceed = null;
        if (ratifyList.size() > count) {
            RealChain realChain = new RealChain(request, ratifyList, count + 1);
            Ratify ratify = ratifyList.get(count);
            proceed = ratify.deal(realChain);
        }
        return proceed;
    }
}
