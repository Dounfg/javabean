package com.javabase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	public static void main(String[] args) throws IOException {

		// Selector: multiplexor of SelectableChannel objects
		Selector selector = Selector.open(); // selector is open here

		// ServerSocketChannel: selectable channel for stream-oriented listening
		// sockets
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress socketAddress = new InetSocketAddress("localhost", 1111);

		// Binds the channel's socket to a local address and configures the
		// socket to listen for connections
		serverSocketChannel.bind(socketAddress);

		// Adjusts this channel's blocking mode.
		serverSocketChannel.configureBlocking(false);

		int ops = serverSocketChannel.validOps();
		SelectionKey selectKy = serverSocketChannel.register(selector, ops, null);

		// Infinite loop..
		// Keep server running
		while (true) {

			log("i'm a server and i'm waiting for new connection and buffer select...");
			// Selects a set of keys whose corresponding channels are ready for
			// I/O operations
			selector.select();

			// token representing the registration of a SelectableChannel with a
			// Selector
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> selectionKeysItr = selectionKeys.iterator();

			while (selectionKeysItr.hasNext()) {
				SelectionKey myKey = selectionKeysItr.next();
				printKeyInfo(myKey);
				// Tests whether this key's channel is ready to accept a new
				// socket connection
				if (myKey.isAcceptable()) {
					SocketChannel socketChannel = serverSocketChannel.accept();

					// Adjusts this channel's blocking mode to false
					socketChannel.configureBlocking(false);

					// Operation-set bit for read operations
					socketChannel.register(selector, SelectionKey.OP_READ);
					log("Connection Accepted: " + socketChannel.getLocalAddress() + "\n");

				} else if (myKey.isReadable()) {// Tests whether this key's
												// channel is ready for reading

					SocketChannel socketChannel = (SocketChannel) myKey.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(256);
					socketChannel.read(byteBuffer);
					String result = new String(byteBuffer.array()).trim();

					log("Message received: " + result);

					if (result.equals("Netflix")) {
						socketChannel.close();
						log("\nIt's time to close connection as we got last company name 'Netflix'");
						log("\nServer will keep running. Try running client again to establish new connection");
					}
				}
				selectionKeysItr.remove();
			}
		}
	}

	private static void log(String str) {
		System.out.println(str);
	}

	private static void printKeyInfo(SelectionKey sk) {
		String s = new String();
		s = "Att: " + (sk.attachment() == null ? "no" : "yes");
		s += ", Read: " + sk.isReadable();
		s += ", Acpt: " + sk.isAcceptable();
		s += ", Cnct: " + sk.isConnectable();
		s += ", Wrt: " + sk.isWritable();
		s += ", Valid: " + sk.isValid();
		s += ", Ops: " + sk.interestOps();
		log(s);
	}
}
