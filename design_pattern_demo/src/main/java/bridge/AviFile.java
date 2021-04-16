package bridge;

/**
 * @author wangxing
 * @date 2021/2/27 12:59
 */
public class AviFile implements VideoFile {

    @Override
    public void decode(String fileName) {
        System.out.println("avi视频文件：" + fileName);
    }

}
