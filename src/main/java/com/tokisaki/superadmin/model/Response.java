package com.tokisaki.superadmin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
	private boolean succeedFlag = true;
	private String msg = "OKOKOK";
	private T entity;

}
