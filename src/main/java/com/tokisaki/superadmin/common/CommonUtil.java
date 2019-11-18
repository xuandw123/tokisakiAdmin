package com.tokisaki.superadmin.common;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tokisaki.superadmin.domain.User;



public class CommonUtil {
	  public static Optional<User> getCurrentUser() {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      if (authentication == null || !authentication.isAuthenticated()) {
	            return Optional.empty();
	        }
	      return Optional.of(((User) authentication.getPrincipal()));
	    }
	  
	  public static String ext(String filename) {
	        int index = filename.lastIndexOf(".");
	 
	        if (index == -1) {
	            return null;
	        }
	        String result = filename.substring(index + 1);
	        return result;
		}
}
