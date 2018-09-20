package com.zxm.me;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author
 * @Description
 * @Date Create in 下午 2:39 2018/9/18 0018
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {
    private ChatFrame frame;
    public NettyClientHandler(ChatFrame frame){
        this.frame = frame;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg){
        System.out.println("服务端返回的数据："+msg);
        frame.print(msg);
    }

}
