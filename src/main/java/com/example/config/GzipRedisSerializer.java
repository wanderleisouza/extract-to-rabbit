package com.example.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import lombok.Cleanup;

public class GzipRedisSerializer<T> implements RedisSerializer<T> {

	private RedisSerializer<T> innerSerializer;

	public GzipRedisSerializer(RedisSerializer<T> innerSerializer) {
		this.innerSerializer = innerSerializer;
	}

	public static final int BUFFER_SIZE = 4096;

	@Override
	public byte[] serialize(T graph) throws SerializationException {

		if (graph == null)
			return new byte[0];

		try {
			byte[] bytes = innerSerializer.serialize(graph);
			@Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();
			@Cleanup GZIPOutputStream gzip = new GZIPOutputStream(bos);

			gzip.write(bytes);
			gzip.close();
			byte[] result = bos.toByteArray();

			return result;
		} catch (Exception e) {
			throw new SerializationException("Gzip serialize exception", e);
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {

		if (bytes == null || bytes.length == 0)
			return null;

		try {
			@Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();
			@Cleanup ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			@Cleanup GZIPInputStream gzip = new GZIPInputStream(bis);

			byte[] buff = new byte[BUFFER_SIZE];
			int n;
			while ((n = gzip.read(buff, 0, BUFFER_SIZE)) > 0) {
				bos.write(buff, 0, n);
			}
			T result = (T) innerSerializer.deserialize(bos.toByteArray());

			return result;
		} catch (Exception e) {
			throw new SerializationException("Gzip deserialize exception", e);
		}

	}
}
