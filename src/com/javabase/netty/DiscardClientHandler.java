package com.javabase.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

	private ByteBuf content;
	private ChannelHandlerContext ctx;

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		this.ctx = ctx;

		// Initialize the message.
		content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

		// Send the initial messages.
		generateTraffic();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		content.release();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// Server is supposed to send nothing, but if it sends something,
		// discard it.
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

	long counter;

	private void generateTraffic() {
		// Flush the outbound buffer to the socket.
		// Once flushed, generate the same amount of traffic again.
		ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
	}

	private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
		@Override
		public void operationComplete(ChannelFuture future) {
			if (future.isSuccess()) {
				generateTraffic();
			} else {
				future.cause().printStackTrace();
				future.channel().close();
			}
		}
	};
}
