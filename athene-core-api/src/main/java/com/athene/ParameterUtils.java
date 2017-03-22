/**
 * 
 */
package com.athene;

import java.io.Serializable;
import java.util.Arrays;

import IceInternal.BasicStream;

/**
 * @author zhaochf
 *
 */
public abstract class ParameterUtils {

	private static final Class<?>[] parameterTypes = new Class<?>[] { boolean.class, Boolean.class, byte.class,
			Byte.class, short.class, Short.class, int.class, Integer.class, long.class, Long.class, float.class,
			Float.class, double.class, Double.class, String.class };

	public static Object readParameter(BasicStream bs, Class<?> parameterType) {
		int position = Arrays.binarySearch(parameterTypes, parameterType);
		Object value = null;
		switch (position) {
		case 0:
		case 1:
			value = bs.readBool();
			break;
		case 2:
		case 3:
			value = bs.readByte();
			break;
		case 4:
		case 5:
			value = bs.readShort();
			break;
		case 6:
		case 7:
			value = bs.readInt();
			break;
		case 8:
		case 9:
			value = bs.readLong();
			break;
		case 10:
		case 11:
			value = bs.readFloat();
			break;
		case 12:
		case 13:
			value = bs.readDouble();
			break;
		case 14:
		case 15:
			value = bs.readString();
			break;
		default:
			value = bs.readSerializable();
		}
		
		return value;
	}

	public static void writeParameter(BasicStream bs, Class<?> parameterType, Object value) {
		int position = Arrays.binarySearch(parameterTypes, parameterType);
		switch (position) {
		case 0:
		case 1:
			bs.writeBool((boolean) value);
			break;
		case 2:
		case 3:
			bs.writeByte((byte) value);
			break;
		case 4:
		case 5:
			bs.writeShort((short) value);
			break;
		case 6:
		case 7:
			bs.writeInt((int) value);
			break;
		case 8:
		case 9:
			bs.writeLong((long) value);
			break;
		case 10:
		case 11:
			bs.writeFloat((float) value);
			break;
		case 12:
		case 13:
			bs.writeDouble((double) value);
			break;
		case 14:
		case 15:
			bs.writeString((String) value);
			break;
		default:
			bs.writeSerializable((Serializable) value);
		}
	}
}
