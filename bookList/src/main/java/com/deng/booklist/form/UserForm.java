package com.deng.booklist.form;

import org.springframework.beans.BeanUtils;

import com.deng.booklist.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserForm {
	private long id;
	
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	private int phone;
	
	private String email;
	
	private class UserFormConvert implements FormConvert<UserForm, User> {
		@Override
		public User convert(UserForm userForm) {
			User user = new User();
			BeanUtils.copyProperties(userForm, user);
			return user;
		}
	}
	
	public User convertToUser() {
		User user = new UserFormConvert().convert(this);
		return user;
	}
}
