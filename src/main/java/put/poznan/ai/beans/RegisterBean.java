package put.poznan.ai.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import put.poznan.ai.models.User;
import put.poznan.ai.services.UserService;

public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 7607769702560970547L;

	@Autowired
	private transient UserService userService;

	private User user;

	public RegisterBean() {
		user = new User();
	}

	public void register() {

		userService.insertUser(user);

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
