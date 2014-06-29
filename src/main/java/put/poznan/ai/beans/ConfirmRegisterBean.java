package put.poznan.ai.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import put.poznan.ai.models.UserRole;
import put.poznan.ai.services.UserService;

public class ConfirmRegisterBean implements Serializable {

	private static final long serialVersionUID = -2659019583015842573L;
	
	@Autowired
	private transient UserService userService;
	
	public boolean confirm(String id, String token) {
		if (id == null || token == null)
			return false;
		UserRole user = new UserRole();
		user.setUser_role_id(id);
		user.setToken(token);
		int n = userService.activateUser(user);
		if (n == 0) 
			return true;
		else
			return false;
	}

}
