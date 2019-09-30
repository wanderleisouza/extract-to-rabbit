package com.example.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Offer implements Serializable {
	
	private static final long serialVersionUID = -5584207855795740529L;
	
	private String id;
	private String name;
	private BigDecimal discount;

}
