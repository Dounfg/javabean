package com.javabase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class NIOClient {
	public static void main(String[] args) throws IOException, InterruptedException {

		InetSocketAddress socketAddress = new InetSocketAddress("localhost", 1111);
		SocketChannel socketChannel = SocketChannel.open(socketAddress);

		log("Connecting to Server on port 1111...");

		ArrayList<String> companyDetails = new ArrayList<String>();

		companyDetails.add("Facebook");
		companyDetails.add("Twitter");
		companyDetails.add("IBM");
		companyDetails.add("Google");
		companyDetails.add("Netflix");

		for (String companyName : companyDetails) {

			byte[] message = new String(companyName).getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(message);
			socketChannel.write(buffer);

			log("sending: " + companyName);
			buffer.clear();

			Thread.sleep(100);
		}
		socketChannel.close();
	}

	private static void log(String str) {
		System.out.println(str);
	}
}
