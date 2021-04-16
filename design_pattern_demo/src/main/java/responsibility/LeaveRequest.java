package responsibility;

/**
 * @author wangxing
 * @date 2021/2/27 21:11
 */
public class LeaveRequest {

    private String name;
    private int num;
    private String content;

    public LeaveRequest(String name, int num, String content) {
        this.name = name;
        this.num = num;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public String getContent() {
        return content;
    }
}
