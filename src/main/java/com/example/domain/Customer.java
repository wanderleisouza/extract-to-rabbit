package com.example.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
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
	
	private String id;
	private String name;
	private Category category;
	
}
