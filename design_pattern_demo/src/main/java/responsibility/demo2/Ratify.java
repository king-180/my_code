package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 17:15
 */
public interface Ratify {
    /**
     * 处理请求
     *
     * @param chain 职责链
     * @return 处理结果
     */
    Result deal(Chain chain);

    interface Chain {
        /**
         * 获取当前请求
         *
         * @return 当前请求
         */
        Request request();

        /**
         * 转发请求
         *
         * @param request 当前请求
         * @return 转发结果
         */
        Result proceed(Request request);

    }

}
