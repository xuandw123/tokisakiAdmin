package com.tokisaki.superadmin.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class UserScore {
	@Id 
	private String userId;
	private BigDecimal taskScore;

}
