package put.poznan.ai.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import put.poznan.ai.common.BaseHelper;
import put.poznan.ai.common.EmailValidator;
import put.poznan.ai.models.User;
import put.poznan.ai.services.UserService;

public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 7607769702560970547L;

	@Autowired
	private transient UserService userService;

	private User user;
	
	private String oldPass;

	public RegisterBean() {
		user = new User();
		user.setEnabled(false);
	}

	public boolean validateUser() {
		if (user.getUsername() == null || user.getPassword() == null
				|| user.getName() == null || user.getSurname() == null) {
			BaseHelper
					.showErrorMessage(BaseHelper.getLocaleString("register.notFilled"));
			return false;
		}
		Pattern pattern = Pattern.compile(EmailValidator.EMAIL_PATTERN);
		if (!pattern.matcher(user.getUsername()).matches()) {
			BaseHelper.showErrorMessage(BaseHelper.getLocaleString("register.validatorEmail"));
			return false;
		}
		return true;
	}

	public String register() {
		if (!validateUser()) {
			return null;
		}
		user.setPassword(BaseHelper.convertAsMd5(user.getPassword()));
		switch (userService.insertUser(user)) {
			case -1:
				BaseHelper.showErrorMessage(BaseHelper.getLocaleString("common.dbError"));
				return null;
			case -2:
				BaseHelper.showErrorMessage(BaseHelper.getLocaleString("register.userExists"));
				return null;
		}
		return "register";
	}
	
	public String changePass() {
		if (oldPass.equals(user.getPassword())) {
			BaseHelper.showErrorMessage(BaseHelper.getLocaleString("changePass.samePass"));
			return null;
		}
		user.setUsername(BaseHelper.getUsername());
		if (userService.updateUser(user) != 0) {
			BaseHelper.showErrorMessage(BaseHelper.getLocaleString("changePass.error"));
			return null;
		}
		return "changePass";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

}
