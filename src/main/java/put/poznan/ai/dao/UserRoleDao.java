package put.poznan.ai.dao;

import java.util.List;

import put.poznan.ai.models.UserRole;

public interface UserRoleDao extends BaseDao<UserRole> {
	
	public List<UserRole> selectByUsername(String username);
	
	public List<UserRole> selectById(String id);

}
