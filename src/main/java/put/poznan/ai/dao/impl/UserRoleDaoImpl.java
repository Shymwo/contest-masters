package put.poznan.ai.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import put.poznan.ai.dao.UserRoleDao;
import put.poznan.ai.models.UserRole;

@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserRole> selectAll() {
		List<UserRole> roles = null;
		Session session = getSessionFactory().openSession();
		try {
			roles = session.createQuery("from user_roles").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return roles;
	}

}
