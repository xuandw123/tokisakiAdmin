package com.tokisaki.superadmin.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tokisaki.superadmin.model.Response;

@ControllerAdvice
public class ExceptionHandlingController {
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
		Response<Exception> errorResponse = new Response<Exception>();
		errorResponse.setSucceedFlag(false);
		errorResponse.setMsg(ex.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.ACCEPTED);
	}
}
