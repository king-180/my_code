/**
 * @author wangxing
 * @date 2021/4/22 17:17
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        // 同一个线程共享数据
        Share share = new Share();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                share.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("--------------------");
                System.out.println(Thread.currentThread().getName() + " 取出 " + share.getContent());
            }, "t" + (i + 1)).start();
        }
    }
}

class Share {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    public String getContent() {
//        return content;
        return threadLocal.get();
    }

    public void setContent(String content) {
//        this.content = content;
        threadLocal.set(content);
    }
}
