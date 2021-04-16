package builder.builder1;

/**
 * @author wangxing
 * @date 2021/2/25 13:40
 */
public class Bike {
    private String frame;
    private String seat;

    public Bike() {
    }

    public Bike(String frame, String seat) {
        this.frame = frame;
        this.seat = seat;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "frame='" + frame + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
