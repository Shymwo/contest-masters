package put.poznan.ai.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import put.poznan.ai.dao.UserDao;
import put.poznan.ai.models.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> selectAll() {
		List<User> users = null;
		Session session = getSessionFactory().openSession();
		try {
			users = session.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> selectByUsername(String username) {
		List<User> users = null;
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from User where username = :username");
			query.setParameter("username", username);
			users = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

}