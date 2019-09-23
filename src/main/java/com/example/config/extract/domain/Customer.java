package com.example.config.extract.domain;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RedisHash("Customer")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Customer implements Serializable {

	private static final long serialVersionUID = -7148364848429252590L;
	
	public enum Category {
		
		MEMBER,
		SILVER_ELITE,
		GOLD_ELITE,
		PLATINUM_ELITE,
		TITANIUM_ELITE,
		AMBASSADOR_ELITE

	}
	
	private final String id;
	private final String name;
	private final Category category;
	
}
