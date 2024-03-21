package com.javabase.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {
	public static void main(String args[]) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("T:/tdrive.bat", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(128);
		int bytesRead = -1;
		while ((bytesRead = inChannel.read(buf)) != -1) {
			// System.out.println("Read " + bytesRead);
		}
		buf.flip();
		// while (buf.hasRemaining()) {
		// System.out.print((char) buf.get());
		// }

		for (int i = 0; i < 20; i++) {
			buf.get();
		}

		while (buf.hasRemaining()) {
			// System.out.print((char) buf.get());
		}

		buf.clear();
		aFile.close();

		// bufferTest();
		// bufferEndianTest();

		streamChannelTest();
	}

	private static void streamChannelTest() throws Exception {
		FileInputStream fis = null;
		FileChannel inChannel = null;
		try {
			fis = new FileInputStream(new File("t:/tdrive.bat"));
			inChannel = fis.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(128);
			int bytesRead = -1;
			while ((bytesRead = inChannel.read(buf)) != -1) {
				System.out.println("Read " + bytesRead);
				buf.flip();
				while (buf.hasRemaining()) {
					System.out.print((char) buf.get());
				}
				buf.clear();
			}
		} finally {
			fis.close();
			inChannel.close();
		}

	}

	private static void bufferEndianTest() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		System.out.println(buffer.isDirect());
		System.out.println("Default java endian: " + buffer.order().toString());

		// buffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println("Current buffer endian: " + buffer.order().toString());

		buffer.putShort(0, (short) 1);
		System.out.println("取出0:" + buffer.get(0));
		System.out.println("取出1:" + buffer.get(1));

	}

	private static void bufferTest() {
		ByteBuffer buf = ByteBuffer.allocate(128);

		// write-read
		buf.put("hello1".getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			System.out.print((char) buf.get());
		}
		buf.rewind();
		while (buf.hasRemaining()) {
			System.out.print((char) buf.get());
		}

		// buf.put("hello2".getBytes());
		// buf.flip();
		// while (buf.hasRemaining()) {
		// System.out.print((char) buf.get());
		// }

	}
}
