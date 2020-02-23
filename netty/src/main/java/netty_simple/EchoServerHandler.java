package netty_simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 接受并响应事件通知  所有事务处理逻辑  每个方法都可以被重写以挂钩到事件生命周期上
 * 其中ChannelInboundHandlerAdapter 适配器提供了ChannelInboundHandler的默认实现
 * 每个channel都拥有ChannelPipeline，持有channelHanler的实例链
 * channelRead      每个传入的消息都要调用
 * channelReadComplete  通知调用读取最后一条消息
 * exceptionCaught      读取操作期间，异常抛出调用
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in=(ByteBuf)msg;
        System.out.println("Server received: "+in.toString(CharsetUtil.UTF_8));
        ctx.write(in);      //写给发送者，不冲刷出站消息
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);  //冲刷节点，并关闭channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
