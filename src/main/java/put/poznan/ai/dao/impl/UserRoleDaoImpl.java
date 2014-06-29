package put.poznan.ai.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
			roles = session.createQuery("from UserRole").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return roles;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserRole> selectByUsername(String username) {
		List<UserRole> roles = null;
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from UserRole where username = :username");
			query.setParameter("username", username);
			roles = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return roles;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserRole> selectById(String id) {
		List<UserRole> roles = null;
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from UserRole where user_role_id = :id");
			query.setParameter("id", id);
			roles = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return roles;
	}

}
