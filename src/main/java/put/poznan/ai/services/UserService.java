package put.poznan.ai.services;

import put.poznan.ai.models.User;
import put.poznan.ai.models.UserRole;

public interface UserService {

	public int insertUser(User user);
	
	public int activateUser(UserRole user);
	
	public int resetPassword(User user);
	
	public int updateUser(User user);

}
