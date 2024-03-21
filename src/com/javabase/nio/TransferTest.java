package com.javabase.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferTest {
	public static void main(String args[]) throws Exception {
		testTo();
	}

	private static void testFrom() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("t:/tdrive.bat", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("t:/tdrive2.bat", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
	}

	private static void testTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("t:/tdrive.bat", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("t:/tdrive2.bat", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		fromChannel.transferTo(position, count, toChannel);
	}
}
