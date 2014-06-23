package put.poznan.ai.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import put.poznan.ai.dao.impl.UserDaoImpl;
import put.poznan.ai.models.User;
import put.poznan.ai.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl userDao;

	@Transactional
	public int insertUser(User user) {

		return 0;
	}
}
