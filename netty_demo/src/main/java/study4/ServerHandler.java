package study4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/2/22 14:36
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + " [客户端]" + channel.remoteAddress() + " 加入聊天\n");
        channelGroup.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) + " [客户端]" + ctx.channel().remoteAddress() + " 上线了~");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) + " [客户端]" + ctx.channel().remoteAddress() + " 下线了~");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + " [客户端]" + channel.remoteAddress() + " 离开了\n");
        System.out.println("channelGroup.size() = " + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) throws Exception {
        Channel channel = channelHandlerContext.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + " [客户]" + channel.remoteAddress() + "发送了消息：" + o + "\n");
            } else {
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + " [自己]" + ch.remoteAddress() + "发送了消息：" + o + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
