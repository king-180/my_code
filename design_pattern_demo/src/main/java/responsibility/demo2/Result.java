package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 17:12
 */
public class Result {

    private boolean isRatify;
    private String info;

    public Result() {
    }

    public Result(boolean isRatify, String info) {
        this.isRatify = isRatify;
        this.info = info;
    }

    public boolean isRatify() {
        return isRatify;
    }

    public String getInfo() {
        return info;
    }

    public void setRatify(boolean ratify) {
        isRatify = ratify;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isRatify=" + isRatify +
                ", info='" + info + '\'' +
                '}';
    }
}
