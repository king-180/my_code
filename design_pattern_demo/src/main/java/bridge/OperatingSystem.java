package bridge;

/**
 * @author wangxing
 * @date 2021/2/27 13:02
 */
public abstract class OperatingSystem {

    protected VideoFile videoFile;

    public OperatingSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public abstract void play(String fileName);

}
