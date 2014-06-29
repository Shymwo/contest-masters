package put.poznan.ai.dao;

import java.util.List;

import put.poznan.ai.models.User;

public interface UserDao extends BaseDao<User> {

	public List<User> selectByUsername(String username);

}
