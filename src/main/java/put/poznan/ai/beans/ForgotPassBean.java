package put.poznan.ai.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import put.poznan.ai.common.BaseHelper;
import put.poznan.ai.models.User;
import put.poznan.ai.services.UserService;

public class ForgotPassBean implements Serializable {

	private static final long serialVersionUID = -8328594471058899889L;

	@Autowired
	private transient UserService userService;
	
	private User user;
	
	public ForgotPassBean() {
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String reset() {
		if (userService.resetPassword(user) != 0) {
			BaseHelper.showErrorMessage(BaseHelper.getLocaleString("forgot.error"));
			return null;
		}
        return "reset";
	}

}
