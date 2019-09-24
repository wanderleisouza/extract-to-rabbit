package com.example.config.extract.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Resource
	private RedisTemplate<String, Object> template;

	public void setKey(String mapName, Map<String, Object> modelMap) {
		HashOperations<String, String, Object> hps = template.opsForHash();
		hps.putAll(mapName, modelMap);
	}

	public Map<String, Object> getMapValue(String mapName) {
		HashOperations<String, String, Object> hps = this.template.opsForHash();
		return hps.entries(mapName);
	}

	public Object getValue(String mapName, String hashKey) {
		HashOperations<String, String, Object> hps = this.template.opsForHash();
		return hps.get(mapName, hashKey);
	}

	public void deleteData(List<String> keys) {
		template.setKeySerializer(new JdkSerializationRedisSerializer());
		template.delete(keys);
	}

}