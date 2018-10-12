package com.bmw.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成自增长14位字符串
 * 使用方法：ObjectId.get()
 * @author jiangying 
 *
 */
public class ObjectId implements Comparable<ObjectId>, Serializable {

	private static final long serialVersionUID = 3670079982654483072L;

	private static final int LOW_ORDER_THREE_BYTES = 0x00ffffff;//16进制

	private static final AtomicInteger NEXT_COUNTER = new AtomicInteger(
			new SecureRandom().nextInt());//原子操作的Integer的类

	private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };//

	private final int timestamp;
	private final int counter;

	/**
	 * Gets a new object id.
	 *
	 * @return the new id
	 */
	public static String get() {
		return new ObjectId()+"";
	}

	/**
	 * 获取自动递增计数器的当前值。
	 *
	 * @return the current counter value.
	 */
	public static int getCurrentCounter() {
		return NEXT_COUNTER.get();
	}

	/**
	 * Create a new object id.
	 */
	public ObjectId() {
		this(new Date());
	}

	/**
	 * 构造一个使用给定日期的新实例。
	 *
	 * @param date
	 *            the date
	 */
	public ObjectId(final Date date) {
		this(dateToTimestampSeconds(date), NEXT_COUNTER.getAndIncrement(),
				false);
	}

	private ObjectId(final int timestamp, final int counter,
			final boolean checkCounter) {
		if (checkCounter && ((counter & 0xff000000) != 0)) {
			throw new IllegalArgumentException(
					"The counter must be between 0 and 16777215 (it must fit in three bytes).");
		}
		this.timestamp = timestamp;
		this.counter = counter & LOW_ORDER_THREE_BYTES;
	}


	/**
	 * 从给定的字节数组构造一个新的实例
	 *
	 * @param bytes
	 *            the byte array
	 * @throws IllegalArgumentException
	 *             if array is null or not of length 12
	 */
	public ObjectId(final byte[] bytes) {
		if (bytes == null) {
			throw new IllegalArgumentException();
		}
		if (bytes.length != 12) {
			throw new IllegalArgumentException("need 12 bytes");
		}

		timestamp = makeInt(bytes[0], bytes[1], bytes[2], bytes[3]);
		counter = makeInt((byte) 0, bytes[9], bytes[10], bytes[11]);
	}

	/**
	 * 调用位移方法进行一系列计算 转换到一个字节数组
	 * order.
	 *
	 * @return the byte array
	 */
	public byte[] toByteArray() {
		byte[] bytes = new byte[7];
		bytes[0] = int3(timestamp);
		bytes[1] = int2(timestamp);
		bytes[2] = int1(timestamp);
		bytes[3] = int0(timestamp);
		bytes[4] = int2(counter);
		bytes[5] = int1(counter);
		bytes[6] = int0(counter);
		return bytes;
	}

	/**
	 * 获取时间戳（UNIX纪元以来的秒数）。
	 *
	 * @return the timestamp
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * getCounter
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * getDate 
	 *
	 * @return the Date
	 */
	public Date getDate() {
		return new Date(timestamp * 1000L);
	}

	/**
	 * 将此实例为一个24字节的十六进制字符串表示形式.
	 *
	 * @return a string representation of the ObjectId in hexadecimal format
	 */
	public String toHexString() {
		char[] chars = new char[14];
		int i = 0;
		for (byte b : toByteArray()) {
			chars[i++] = HEX_CHARS[b >> 4 & 0xF];
			chars[i++] = HEX_CHARS[b & 0xF];
		}
		return new String(chars);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ObjectId objectId = (ObjectId) o;

		if (counter != objectId.counter) {
			return false;
		}

		if (timestamp != objectId.timestamp) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = timestamp;
		result = 31 * result + counter;
		return result;
	}

	@Override
	public int compareTo(final ObjectId other) {
		if (other == null) {
			throw new NullPointerException();
		}

		byte[] byteArray = toByteArray();
		byte[] otherByteArray = other.toByteArray();
		for (int i = 0; i < 12; i++) {
			if (byteArray[i] != otherByteArray[i]) {
				return ((byteArray[i] & 0xff) < (otherByteArray[i] & 0xff)) ? -1
						: 1;
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return toHexString();
	}

	private static int dateToTimestampSeconds(final Date time) {
		return (int) (time.getTime() / 1000);
	}

	//& 位运算符主要针对两个二进制数的位进行逻辑运算
			/*“a”的值是129，转换成二进制就是10000001，而“b”的值是128，转换成二进制就是10000000。
			 * 根据与运算符的运算规律，只有两个位都是1，结果才是1，可以知道结果就是10000000，即128。*/
	private static int makeInt(final byte b3, final byte b2, final byte b1,
			final byte b0) {
		// CHECKSTYLE:OFF
		return (((b3) << 24) | ((b2 & 0xff) << 16) | ((b1 & 0xff) << 8) | ((b0 & 0xff)));
		// CHECKSTYLE:ON
	}

	private static byte int3(final int x) {
		return (byte) (x >> 24);
	}

	private static byte int2(final int x) {
		return (byte) (x >> 16);
	}

	private static byte int1(final int x) {
		return (byte) (x >> 8);
	}

	private static byte int0(final int x) {
		return (byte) (x);
	}

	/*private static byte short1(final short x) {
		return (byte) (x >> 8);
	}

	private static byte short0(final short x) {
		return (byte) (x);
	}*/
	
	
}
