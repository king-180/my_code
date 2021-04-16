package study3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author wangxing
 * @date 2021/2/22 14:06
 */
public class NettyByteBuf1 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello world!", StandardCharsets.UTF_8);
        if (buf.hasArray()) {
            byte[] content = buf.array();
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("buf = " + buf);
            System.out.println(buf.arrayOffset());
            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.capacity());
            System.out.println(buf.readableBytes());
        }
    }
}
