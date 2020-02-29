package com.distribuida.gateway.utils;

import io.netty.buffer.ByteBuf;

public class ByteBufUtils {

	public static byte[] extract( ByteBuf result ) {
		
		int size = result.readableBytes();
		byte[] data = new byte[size];
		result.readBytes( data );
		
		return data;
	}
}
