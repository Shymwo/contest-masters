package put.poznan.ai.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import put.poznan.ai.common.BaseHelper;
import put.poznan.ai.common.MailUtil;
import put.poznan.ai.dao.impl.UserDaoImpl;
import put.poznan.ai.dao.impl.UserRoleDaoImpl;
import put.poznan.ai.models.User;
import put.poznan.ai.models.UserRole;
import put.poznan.ai.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl userDao;

	@Autowired
	private UserRoleDaoImpl userRoleDao;

	@Autowired
	private transient MailUtil mailUtil;

	private String createEmailBody(String name, String link) {
		return "Witaj "
				+ name
				+ "!\n"
				+ "\n"
				+ "Aby potwierdzić rejestrację na portalu Contest Masters, kliknij w poniższy link:\n"
				+ link
				+ "\n"
				+ "Jeśli nie rejestrowałeś się na portalu, to zignoruj tego maila.\n"
				+ "\n" + "Pozdrawiamy,\n" + "Ekipa Contest Masters";
	}
	
	private String createEmailBody2(String name, String pass) {
		return "Witaj "
				+ name
				+ "!\n"
				+ "\n"
				+ "Twoje nowe hasło to:\n"
				+ pass
				+ "\n"
				+ "\n" + "Pozdrawiamy,\n" + "Ekipa Contest Masters";
	}

	@Transactional
	public int insertUser(User user) {
		if (userDao.selectByUsername(user.getUsername()).size() != 0) {
			return -2;
		}
		UserRole role = new UserRole();
		role.setUsername(user.getUsername());
		role.setRole(BaseHelper.DEFAULT_ROLE);
		role.setToken(BaseHelper.getRandomString());
		int n = userDao.insert(user);
		int m = userRoleDao.insert(role);
		String id = userRoleDao.selectByUsername(role.getUsername()).get(0)
				.getUser_role_id();
		if (n == 0 && m == 0) {
			mailUtil.sendMail(
					user.getUsername(),
					BaseHelper.getLocaleString("register.mailTitle"),
					createEmailBody(user.getName(), BaseHelper.HOST
							+ "/app/confirmRegister?id=" + id + "&token="
							+ role.getToken()));
		} else {
			if (n == 0)
				userDao.delete(user);
			if (m == 0)
				userRoleDao.delete(role);
			return -1;
		}
		return 0;
	}

	@Transactional
	public int activateUser(UserRole token) {
		List<UserRole> checks = userRoleDao.selectById(token.getUser_role_id());
		if (checks.size() != 1)
			return -1;
		UserRole role = checks.get(0);
		if (token.getToken().equals(role.getToken())) {
			role.setToken(null);
			User user = userDao.selectByUsername(role.getUsername()).get(0);
			user.setEnabled(true);
			int n = userDao.update(user);
			int m = userRoleDao.update(role);
			if (n == 0 && m == 0)
				return 0;
			else
				return -3;
		} else
			return -2;
	}

	@Transactional
	public int resetPassword(User user) {
		List<User> users = userDao.selectByUsername(user.getUsername());
		if (users.size() != 1)
			return -2;
		User realUser = users.get(0);
		String newPass = BaseHelper.getRandomString();
		realUser.setPassword(BaseHelper.convertAsMd5(newPass));
		int n = userDao.update(realUser);
		if (n == 0) {
			mailUtil.sendMail(
				realUser.getUsername(),
				BaseHelper.getLocaleString("forgot.mailTitle"),
				createEmailBody2(realUser.getName(), newPass));
		}
		return n;
	}

	@Transactional
	public int updateUser(User user) {
		List<User> users = userDao.selectByUsername(user.getUsername());
		if (users.size() != 1)
			return -2;
		User realUser = users.get(0);
		realUser.setPassword(BaseHelper.convertAsMd5(user.getPassword()));
		int n = userDao.update(realUser);
		return n;
	}

}
